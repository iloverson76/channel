package com.deepexi.promotion.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-07 18:50
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 线程池核心池的大小
     */
    private static final Integer CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    /**
     * 线程池的最大线程数
     */
    private static final Integer MAXIMUM_POOL_SIZE  = Runtime.getRuntime().availableProcessors() * 2 + 1;

    /**
     * 阻塞队列大小
     */
    private static final Integer MAX_WORK_QUEUE_SIZE = 200;

    /**
     * 创建线程池的名称
     */
    private static final String THREAD_NAME = "xpaas-promotion-center-thread-%d";

    @Bean
    public ThreadFactory threadFactory() {
        return new BasicThreadFactory.Builder()
                .namingPattern(THREAD_NAME)
                .daemon(false)
                .build();
    }

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                60L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(MAX_WORK_QUEUE_SIZE),
                threadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

}
