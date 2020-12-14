package lv.afilatov.aggregatorapi.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadConfiguration {

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(100);
    }
}
