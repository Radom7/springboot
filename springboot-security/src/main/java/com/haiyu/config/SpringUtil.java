package com.haiyu.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.annotation.Annotation;

/**
 * @Title: SpringUtil
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/7 16:27
 */
@Component
public class SpringUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext  = applicationContext;
        }
        System.out.println("--------获取applicationContext----------");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static String[] controllers(Class<? extends Annotation>  clazz){
        return getApplicationContext().getBeanNamesForAnnotation(clazz);
    }
}
