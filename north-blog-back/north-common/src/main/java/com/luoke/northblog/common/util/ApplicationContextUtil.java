package com.luoke.northblog.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *  ApplicationContext 工具类，主要用于获取ApplicationContext对象
 * @author luoke
 * @date 2021/11/2 10:44
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> classType){
        return applicationContext.getBean(classType);
    }

    public static <T> T getBean(String name, Class<T> classType) {
        return applicationContext.getBean(name,classType);
    }
}
