package com.deepexi.channel.config;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;

import java.util.List;
import java.util.stream.Collectors;

/***
 * @ClassName: TenantSqlParserConfig
 * @Description:TODO
 * @author: chenling
 * @Date: 2019/5/8 16:04
 * @version : V1.0.0
 */
@Slf4j
public class TenantSqlParserConfig extends TenantSqlParser {


    /**
     * select 语句处理
     */
    @Override
    public void processSelectBody(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody);
            log.info(selectBody.toString());
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                processSelectBody(withItem.getSelectBody());
            }
            log.info(selectBody.toString());
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            if (operationList.getSelects() != null && operationList.getSelects().size() > 0) {
                operationList.getSelects().forEach(this::processSelectBody);
            }
            log.info(selectBody.toString());
        }
    }

    /**
     * <p>
     * insert 语句处理
     * </p>
     */
    @Override
    public void processInsert(Insert insert) {
        if (getTenantHandler().doTableFilter(insert.getTable().getName())) {
            // 过滤退出执行
            return;
        }

        List<Column> columns = insert.getColumns();

        List<Boolean> list = columns.stream().map(column -> column.getColumnName().equals(getTenantHandler().getTenantIdColumn())).collect(Collectors.toList());

        boolean containsFlag = list.contains(true);

        if (!containsFlag) {
            insert.getColumns().add(new Column(getTenantHandler().getTenantIdColumn()));
        }

        if (insert.getSelect() != null) {
            processPlainSelect((PlainSelect) insert.getSelect().getSelectBody(), true);
        } else if (insert.getItemsList() != null) {
            // fixed github pull/295
            ItemsList itemsList = insert.getItemsList();
            if (itemsList instanceof MultiExpressionList) {
                if (!containsFlag) {
                    ((MultiExpressionList) itemsList).getExprList().forEach(el -> el.getExpressions().add(getTenantHandler().getTenantId()));
                }
            } else {
                if (!containsFlag) {
                    ((ExpressionList) insert.getItemsList()).getExpressions().add(getTenantHandler().getTenantId());
                }
            }
        } else {
            throw ExceptionUtils.mpe("Failed to process multiple-table update, please exclude the tableName or statementId");
        }
    }


    /**
     * <p>
     * update 语句处理
     * </p>
     */
    @Override
    public void processUpdate(Update update) {
        List<Table> tableList = update.getTables();
        Assert.isTrue(null != tableList && tableList.size() < 2,
                "Failed to process multiple-table update, please exclude the statementId");
        if (CollectionUtil.isEmpty(tableList)) {
            return;
        }
        Table table = tableList.get(0);
        if (getTenantHandler().doTableFilter(table.getName())) {
            // 过滤退出执行
            return;
        }
        update.setWhere(this.andExpression(table, update.getWhere()));
    }

    /**
     * <p>
     * delete 语句处理
     * </p>
     */
    @Override
    public void processDelete(Delete delete) {
        if (getTenantHandler().doTableFilter(delete.getTable().getName())) {
            // 过滤退出执行
            return;
        }
        delete.setWhere(this.andExpression(delete.getTable(), delete.getWhere()));
    }


    /**
     * <p>
     * 处理 PlainSelect
     * </p>
     */
    @Override
    protected void processPlainSelect(PlainSelect plainSelect) {
        processPlainSelect(plainSelect, false);
    }

    /**
     * <p>
     * 处理 PlainSelect
     * </p>
     *
     * @param plainSelect
     * @param addColumn   是否添加租户列,insert into select语句中需要
     */
    @Override
    protected void processPlainSelect(PlainSelect plainSelect, boolean addColumn) {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            if (getTenantHandler().doTableFilter(fromTable.getName())) {
                // 过滤退出执行
                return;
            }
            //如果本身查询包含tenantId，则不需要设置
            String s = plainSelect.getWhere().toString();
            if (s.indexOf("tenant_id =")==-1){
                plainSelect.setWhere(builderExpression(plainSelect.getWhere(), fromTable));
            }
            if (addColumn) {
                plainSelect.getSelectItems().add(new SelectExpressionItem(new Column(getTenantHandler().getTenantIdColumn())));
            }
        } else {
            processFromItem(fromItem);
        }
        List<Join> joins = plainSelect.getJoins();
        if (joins != null && joins.size() > 0) {
            joins.forEach(j -> {
                processJoin(j);
                processFromItem(j.getRightItem());
            });
        }
    }

    /**
     * 处理条件
     */
    @Override
    protected Expression builderExpression(Expression expression, Table table) {
        //生成字段名
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(this.getAliasColumn(table, false));
        equalsTo.setRightExpression(getTenantHandler().getTenantId());
        //加入判断防止条件为空时生成 "and null" 导致查询结果为空
        if (expression == null) {
            return equalsTo;
        } else {
            if (expression instanceof BinaryExpression) {
                BinaryExpression binaryExpression = (BinaryExpression) expression;
                if (binaryExpression.getLeftExpression() instanceof FromItem) {
                    processFromItem((FromItem) binaryExpression.getLeftExpression());
                }
                if (binaryExpression.getRightExpression() instanceof FromItem) {
                    processFromItem((FromItem) binaryExpression.getRightExpression());
                }
            }
            return new AndExpression(equalsTo, expression);
        }
    }


    /**
     * <p>
     * 租户字段别名设置<br>
     * tableName.tenantId 或 tableAlias.tenantId
     * </p>
     *
     * @param table               表对象
     * @param needAppendTableName 没有表别名时，是否以表名作为别名
     * @return 字段
     */
    protected Column getAliasColumn(Table table, Boolean needAppendTableName) {
        StringBuilder column = new StringBuilder();
        if (null == table.getAlias()) {
            if (needAppendTableName) {
                column.append(table.getName());
                column.append(StringPool.DOT);
            }
        } else {
            column.append(table.getAlias().getName());
            column.append(StringPool.DOT);
        }
        column.append(getTenantHandler().getTenantIdColumn());
        return new Column(column.toString());
    }


}
