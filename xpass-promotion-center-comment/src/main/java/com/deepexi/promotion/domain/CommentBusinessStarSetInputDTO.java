package com.deepexi.promotion.domain;


import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;


/**
* comment_business_star_set
* @author zhangyanping
*/

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentBusinessStarSetInputDTO extends AbstractObject implements Serializable {

    /**
     * 评价业务主键
     */
    private Long  businessModelConnectId;

    /**
     * 星级模板ID
     */
    private List<Long> starIdList;
    /**
     * 类型
     */
    private Integer type;

    /**
     * 业务id
     */
    private Long businessId;

}

