package com.deepexi.channel.enums;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by donh on 2019/1/8.
 */
public enum ForceDeleteEnum {

    NO(0,"不能强制删除"),
    YES(1,"强制删除"),
    ;

    private Integer code;

    private String msg;

    ForceDeleteEnum(Integer code, String msg) {
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

        for (ForceDeleteEnum type : ForceDeleteEnum.values()) {

            Map<String, String> map = new HashMap<>();

            map.put("code", String.valueOf(type.getCode()));

            String msg=type.getMsg();

            map.put("msg", String.valueOf(msg));

            list.add(map);
        }
        return list;
    }
    public static Set<Integer> getCodes(ForceDeleteEnum... enums) {
        Set<Integer> set = new HashSet<>();
        for (ForceDeleteEnum anEnum : enums) {
            set.add(Integer.valueOf(anEnum.getCode()));
        }
        return set;
    }

}