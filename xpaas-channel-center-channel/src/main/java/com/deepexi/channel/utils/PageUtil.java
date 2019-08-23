package com.deepexi.channel.utils;

import cn.hutool.core.bean.BeanUtil;
import com.deepexi.util.pojo.BeanCopierUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 提供 Page 和 PageInfo 相互转换的方法
 *
 * @author liaoxiaoxin
 * @date 2019/7/5 16:04
 */
public class PageUtil {
    /**
     * PageInfo 转 list
     *
     * @param pageInfo PageInfo
     * @param <T>      泛型(PageInfo中存储的对象类型)
     * @return Page
     */
    public static <T> List<T> changeToPage(PageInfo<T> pageInfo) {
        Page<T> page = new Page<>();
        BeanUtil.copyProperties(pageInfo, page);
        page.addAll(pageInfo.getList());
        return page;
    }

    /**
     * list 转 PageInfo
     *
     * @param page Page
     * @param <T>  泛型(PageInfo中存储的对象类型)
     * @return PageInfo
     */
    public static <T> PageInfo<T> changeToPageInfo(List<T> page) {
        if (page instanceof Page) {
            PageInfo<T> pageInfo = new PageInfo<>();
            BeanCopierUtils.copyProperties(page, pageInfo);
            pageInfo.setList(((Page<T>) page).getResult());
            return pageInfo;
        }
        return new PageInfo<>(page);
    }
}
