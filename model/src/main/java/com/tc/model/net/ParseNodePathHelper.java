package com.tc.model.net;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianchao on 2017/8/3.
 */

public class ParseNodePathHelper {

    public static ParseNodePath getParseNodePath(Type type) {
        Annotation[] annotations = null;
        //如果model是范型
        if (type instanceof ParameterizedType) {
            Class cls = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            annotations = cls.getAnnotations();
        } else if (type instanceof Class) {
            annotations = ((Class) type).getAnnotations();
        }
        if (annotations != null) {
            for (Annotation a : annotations) {
                if (a instanceof ParseNodePath) {
                    return (ParseNodePath) a;
                }
            }
        }
        return null;
    }

    public static List<List<String>> getParsePathList(ParseNodePath path)
            throws IllegalParseNodePathException {
        List<List<String>> pathList = new ArrayList<>();
        String[] pathArray = path.path();
        if (pathArray.length <= 0) {
            throw new IllegalParseNodePathException();
        }
        for (int i = 0; i < pathArray.length; i++) {
            String str = pathArray[i];
            if (!str.startsWith(":") || str.endsWith(":")) {
                throw new IllegalParseNodePathException();
            }
            String[] nodes = str.split(":");
            List<String> nodeList = new ArrayList<>();
            //去掉第一个空白
            for (int j = 1; j < nodes.length; j++) {
                nodeList.add(nodes[j]);
            }
            pathList.add(nodeList);
        }
        return pathList;
    }


    public static class IllegalParseNodePathException extends RuntimeException {

    }
}
