package com.tc.audioplayer.event;

/**
 * 用来提供注册Eventbus时的各种注册类型
 */
public class EventBusRegisterFlags {

    /**
     * NOT_NEED_DEFAULT_REGISTER:   不需要使用默认注册
     * NEED_DEFAULT_REGISTER:   如果在基类中设置这个标志，自动将子类注册为EventBus的订阅者
     * 在具有生命周期的对象(如Activity，Service等)，将采用默认的生命周期进行注册和注销
     */

    public static final int NOT_NEED_DEFAULT_REGISTER = 0x00;
    public static final int NEED_DEFAULT_REGISTER = 0x01;
}
