package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.*;
import com.deepexi.util.extension.ApplicationException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 此接口用于构建和保存评论的数据
 *
 * @author liaoxiaoxin
 * @date 2019/6/20 20:42
 */
public interface CommentDataService {

    /**
     * 关联将要落库的数据
     *
     * @param insertSequence      原子操作类 AtomicLong(用来保存关联序号)
     * @param resourceInputDTOS   输入的资源集合
     * @param labelInputDTOS      输入的标签集合
     * @param starInputDTOS       输入的星评集合
     * @param commentDetailDO     评论明细实体
     * @param commentResourceList 保存关联好的资源集合
     * @param commentLabelList    保存关联好的标签集合
     * @param commentStarList     保存关联好的星评集合
     */
    void associatedData(AtomicLong insertSequence, List<CommentResourceInputDTO> resourceInputDTOS,
                        List<CommentLabelInputDTO> labelInputDTOS, List<CommentStarInputDTO> starInputDTOS,
                        CommentDetailDO commentDetailDO, List<CommentResourceDO> commentResourceList,
                        List<CommentLabelDO> commentLabelList, List<CommentStarDO> commentStarList);

    /**
     * 保存数据
     *
     * @param commentDetailList    评论明细
     * @param commentResourceList  评论资源集合
     * @param commentLabelList     评论标签
     * @param commentStarList      评论星评集合
     * @param commentStatisticList 评论统计集合
     */
    void insertData(List<CommentDetailDO> commentDetailList, List<CommentResourceDO> commentResourceList,
                    List<CommentLabelDO> commentLabelList, List<CommentStarDO> commentStarList,
                    List<CommentStatisticDO> commentStatisticList);


    /**
     * 发布评价：根据前端传入的ID,查询出落库需要的其他字段(eg: 星评别名，星评值等)
     *
     * @param input 发布评价的请求参数
     * @throws ApplicationException 回查数据库时，若查询不到记录会抛出异常
     */
    void queryStarInfoSetToPublishData(CommentInfoInputDTO input) throws ApplicationException;

    /**
     * 追评、回复：根据前端传入的ID,查询出落库需要的其他字段(eg: 星评别名，星评值等)
     *
     * @param input 追评或者回复的请求参数
     * @throws ApplicationException 回查数据库时，若查询不到记录会抛出异常
     */
    void queryStarInfoSetToReplyOrAdditionalData(CommentDetailReplyInputDTO input) throws ApplicationException;


    /**
     * 获取父评论信息,用于追评时去重
     *
     * @param parentId 父评论
     * @return CommentStatisticParamDTO
     */
    CommentStatisticParamDTO getParentStatisticInfo(Long parentId);


    /**
     * 删除评价时，删除(评论、星评、资源、标签)数据，更新统计记录表
     *
     * @param commentDetailDoList 评论ID列表
     * @param commentStarDoList 星评ID列表
     * @param commentResourceDoList 资源ID列表
     * @param commentLabelDoList 标签ID列表
     * @param statisticDoList 统计集合
     */
    void removeData(List<CommentDetailDO> commentDetailDoList, List<CommentStarDO> commentStarDoList,
                    List<CommentResourceDO> commentResourceDoList, List<CommentLabelDO> commentLabelDoList,
                    List<CommentStatisticDO> statisticDoList);
}
