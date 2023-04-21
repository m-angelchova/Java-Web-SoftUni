package bg.softuni.blacklist.web;

import bg.softuni.blacklist.service.BlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Locale;
import java.util.Map;

@Component
public class IpBlackListInterceptor implements HandlerInterceptor {
    private final BlacklistService blacklistService;
    private final ThymeleafViewResolver resolver;

    public IpBlackListInterceptor(BlacklistService blacklistService, ThymeleafViewResolver resolver) {
        this.blacklistService = blacklistService;
        this.resolver = resolver;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = getIpAddressFromRequest(request);

        if (blacklistService.isBlacklisted(ip)){
            View blockedView = resolver.resolveViewName("blocked", Locale.getDefault());
            if (blockedView != null) {
                blockedView.render(Map.of(), request, response);
                //todo - log error
            }
            return false;
        }
        return true;
    }

    private String getIpAddressFromRequest(HttpServletRequest request){
        String ipAddress = null;

        String xffHeader = request.getHeader("X-Forwarded-For");
        if(xffHeader != null && !xffHeader.equals("unknown")){
            int commaIndex = xffHeader.indexOf(",");
            if (commaIndex > 0){
                ipAddress = xffHeader.substring(0, commaIndex - 1);
            }

        }

        if (ipAddress == null){
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

}
