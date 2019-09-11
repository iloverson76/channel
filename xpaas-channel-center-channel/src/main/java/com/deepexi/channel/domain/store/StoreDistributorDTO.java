package com.deepexi.channel.domain.store;

import com.deepexi.channel.domain.SuperEntity;
import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 20:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDistributorDTO extends SuperEntity {

    Long storeId;
    Long distributorId;
    /**经销商code*/
    String distributorCode;
    /**经销商中文名称*/
    String distributorName;
    /**上级经销商ID*/
    Long parentId;
    /**上级经销商名称*/
    String parentName;
    /**上级经销商code*/
    String parentCode;
    /**经销商等级id*/
    Long distributorGradeSystemId;
    /**经销商编码*/
    String distributorGradeSystemCode;
    /**进销商等级体系名称*/
    String distributorGradeSystemName;
}