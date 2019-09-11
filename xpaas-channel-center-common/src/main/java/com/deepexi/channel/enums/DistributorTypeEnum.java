package com.deepexi.channel.enums;

import com.deepexi.util.constant.BaseEnumType;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;

import java.util.*;

/**
 * Created by donh on 2019/1/8.
 */
public enum DistributorTypeEnum{

    DISTRIBUTOR(1,"经销商"),
    FACTORY(2,"厂商"),
    ;

    private Integer code;

    private String msg;

    DistributorTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static List<Map<String,String>> getTypeList() {

        List<Map<String,String>> list = Lists.newArrayList();

        for (DistributorTypeEnum type : DistributorTypeEnum.values()) {

            Map<String, String> map = new HashMap<>();

            map.put("code", String.valueOf(type.getCode()));

            String msg=type.getMsg();

            map.put("msg", String.valueOf(msg));

            list.add(map);
        }
        return list;
    }
    public static Set<Integer> getCodes(DistributorTypeEnum... enums) {
        Set<Integer> set = new HashSet<>();
        for (DistributorTypeEnum anEnum : enums) {
            set.add(Integer.valueOf(anEnum.getCode()));
        }
        return set;
    }

}