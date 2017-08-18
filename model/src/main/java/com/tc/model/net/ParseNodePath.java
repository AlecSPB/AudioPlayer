package com.tc.model.net;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tianchao on 2017/8/3.
 * 用来描述当前解析的节点路径
 * 比如有如下Json串：
 *
 * {
 *     "data":{
 *         "realentry":{
 *             "id":123,
 *             "name":god
 *         }
 *     }
 * }
 *
 * 如果我需要解析"realentry",
 * 只需要在解析的Model类中增加 ParseNodePath，并指定path为":data:realentry"即可
 * 如果多个接口共用一个model，则@ParseNodePath(path = {"path1", "path2"})，path1为接口1的路径，path2为接口2的路径
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParseNodePath {
    String[] path() default ":data";
}
