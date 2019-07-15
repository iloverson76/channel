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
public class CommentModelVO extends AbstractObject {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 应用id
     */
    private Long  appId;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 编码
     */
    private String  code;
    /**
     * 标识码
     */
    @NotBlank(message = "标识码不能为空")
    @Pattern(regexp ="^[a-zA-Z0-9]+$",message = "标识码格式不正确")
    private String identificationCode;
    /**
     * 启用状态 0 禁用 1启用
     */
    @NotNull(message = "是否开启状态不能为空")
    private Integer isEnable;


}

