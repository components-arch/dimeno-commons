package com.dimeno.commons;

import java.util.List;

/**
 * list empty utils
 * Created by wangzhen on 2020/8/19.
 */
public class IList {

    public static <T> boolean isNotEmpty(List<T> list) {
        return !isEmpty(list);
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
