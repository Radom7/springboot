package com.haiyu.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Title: MyWebAppConfigurer
 * @Description: 建造虚拟地址映射本机的图片保存路径
 * @author: youqing
 * @version: 1.0
 * @date: 2018/6/1 15:15
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    // 获取配置文件中图片的路径
    @Value("${cbs.imagesPath}")
    private String mImagesPath;


    // 访问图片方法
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}")) {
            String imagesPath = MyWebAppConfigurer.class.getClassLoader().getResource("").getPath();
            if (imagesPath.indexOf(".jar") > 0) {
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            } else if (imagesPath.indexOf("classes") > 0) {
                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
            mImagesPath = imagesPath;
            System.out.println(mImagesPath);
        }
        LoggerFactory.getLogger(MyWebAppConfigurer.class).info("imagesPath=" + mImagesPath);
        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
        super.addResourceHandlers(registry);
    }
}
