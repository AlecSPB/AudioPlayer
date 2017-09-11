package com.tc.base.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * User: tianchao
 * Date: 15/11/17
 * Time: 下午2:51
 * PS: 学如逆水行舟，不进则退
 */
public final class CollectionUtil {

    public static <T> List<T> intersect(List<T> list1, List<T> list2) {
        ArrayList list = new ArrayList(Arrays.asList(new Object[list1.size()]));
        Collections.copy(list, list1);
        list.retainAll(list2);
        return list;
    }

    public static <T> List<T> asList(T... arr) {
        return arr == null?null:new ArrayList(Arrays.asList(arr));
    }

    public static <T> List<T> union(List<T> list1, List<T> list2) {
        ArrayList list = new ArrayList(Arrays.asList(new Object[list1.size()]));
        Collections.copy(list, list1);
        list.removeAll(list2);
        list.addAll(list2);
        return list;
    }

    public static <T> List<T> diff(List<T> list1, List<T> list2) {
        List unionList = union(list1, list2);
        List intersectList = intersect(list1, list2);
        unionList.removeAll(intersectList);
        return unionList;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> boolean inArray(T t, List<T> list) {
        return t != null && !isEmpty(list)?list.contains(t):false;
    }

    public static <T> void addAll(List<T> list, T... ts) {
        List newList = Arrays.asList(ts);
        list.addAll(newList);
    }

    public static <T> int size(List<T> list) {
        return list == null?0:list.size();
    }
}