package com.deepexi.channel.domain.store;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.channel.domain.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@TableName("cc_store_grade_relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "门店-等级关联表")
public class StoreGradeRelationDO extends SuperEntity {
    @ApiModelProperty(value = "门店Id")
    @TableField(value = "`store_id`")
    private Long storeId;
    @ApiModelProperty(value = "门店等级ID")
    @TableField(value = "`store_grade_Id`")
    private Long storeGradeId;
}
