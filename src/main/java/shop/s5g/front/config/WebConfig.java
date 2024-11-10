package shop.s5g.front.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.s5g.front.filter.LoginStatusInterceptor;
import shop.s5g.front.interceptor.AuthorizationInterceptor;
import shop.s5g.front.service.auth.AuthService;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthService authService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor());
        registry.addInterceptor(new LoginStatusInterceptor());
    }
}
