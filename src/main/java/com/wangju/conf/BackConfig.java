package com.wangju.conf;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by java_zong on 2017/7/5.
 */
@Configurable
@EnableTransactionManagement
@PropertySources(value = {@PropertySource("classpath:db.properties")})
public class BackConfig {

    @Value(value = "${thead.pool.minSize}")
    private Integer theadPoolMinSize;
    @Value(value = "${thead.pool.maxSize}")
    private Integer theadPoolMaxSize;
    @Value(value = "${thead.pool.queueSize}")
    private Integer theadPoolQueueSize;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfiInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 线程池
     * @return
     */
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(theadPoolMinSize);
        taskExecutor.setMaxPoolSize(theadPoolMaxSize);
        taskExecutor.setQueueCapacity(theadPoolQueueSize);
        taskExecutor.initialize();
        return taskExecutor;
    }

}
