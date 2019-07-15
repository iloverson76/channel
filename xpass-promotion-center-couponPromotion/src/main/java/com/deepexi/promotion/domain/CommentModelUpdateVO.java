package com.deepexi.promotion.domain;


import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentModelUpdateVO extends AbstractObject {


    private String name;
    /**
     * 编码
     */
    private String  code;
    /**
     * 标识码
     */
    @Pattern(regexp ="^[a-zA-Z0-9]+$",message = "标识码格式不正确")
    private String identificationCode;
    /**
     * 启用状态 0 禁用 1启用
     */
    private Integer isEnable;


}

