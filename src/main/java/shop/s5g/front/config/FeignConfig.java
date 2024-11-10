package shop.s5g.front.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.s5g.front.utils.AuthTokenHolder;

@Slf4j
@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor globalRequestInterceptor() {
        return requestTemplate -> {
            log.debug("Authorization token: {}", AuthTokenHolder.getToken());
            if (!requestTemplate.headers().containsKey("Authorization")) {
                requestTemplate.header("Authorization", "Bearer " + AuthTokenHolder.getToken());
            } else {
                log.debug("Authorization header already exists, skipping.");
            }
        };
    }
}