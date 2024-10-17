package com.cineinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 100;
    private static final int QUEUE_CAPACITY = 500;
    private static final boolean WAIT_TASK_COMPLETE = true;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        // Queue 가 가득찬 상태에서 추가 작업이 들어오면 발생하는 예외 발생 처리 (요청한 스레드에서 직접 처리하도록 설정)
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 어플리케이션 종료 시 Queue 에 남아있는 모든 작업들이 처리될때까지 기다린 후 종료 처리
        executor.setWaitForTasksToCompleteOnShutdown(WAIT_TASK_COMPLETE);
        executor.initialize();
        return executor;
    }
}
