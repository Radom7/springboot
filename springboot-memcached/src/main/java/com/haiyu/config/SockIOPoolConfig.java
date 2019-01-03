package com.haiyu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Title: SockIOPoolConfig
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/8/15 14:57
 */
@Data
@Component
@ConfigurationProperties(prefix = "memcache")
public class SockIOPoolConfig {
    private String[] servers;

    private Integer[] weights;

    private int initConn;

    private int minConn;

    private int maxConn;

    private long maintSleep;

    private boolean nagle;

    private int socketTO;
}
