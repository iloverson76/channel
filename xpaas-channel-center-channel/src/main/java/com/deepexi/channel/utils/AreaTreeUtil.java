package com.deepexi.channel.utils;

import java.io.Serializable;
import java.util.List;

/**
 * created by chenpeng on 2019-08-27
 */
public class AreaTreeUtil {


    class Category implements Serializable {

        private Long id;

        private Long parentId;

        private List<Category> children;

        private String categoryType;

        private String categoryTypeName;


    }

}
