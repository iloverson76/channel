package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName CommentSystemAppSecretVO
 * @Description TODO
 * @Author zhoujiawen
 * @Date 2019-05-08 14:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentSystemAppSecretVO extends AbstractObject {

    @ApiModelProperty(value = "鉴权标识id")
    private String agentId;

    @ApiModelProperty(value = "鉴权标识密钥")
    private String secret;
}
