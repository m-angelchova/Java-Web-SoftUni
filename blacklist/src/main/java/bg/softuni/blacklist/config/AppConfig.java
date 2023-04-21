package bg.softuni.blacklist.config;

import bg.softuni.blacklist.web.IpBlackListInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    private final IpBlackListInterceptor ipBlInterceptor;

    public AppConfig(IpBlackListInterceptor ipBlInterceptor) {
        this.ipBlInterceptor = ipBlInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipBlInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
