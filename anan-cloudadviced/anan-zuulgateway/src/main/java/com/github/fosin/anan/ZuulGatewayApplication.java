package com.github.fosin.anan;

import com.github.fosin.anan.core.banner.AnanBanner;
import com.github.fosin.anan.oauth2.annotation.EnableAnanResourceServer;
import com.github.fosin.anan.redis.annotation.EnableAnanRedis;
import com.github.fosin.anan.swagger.annotation.EnableAnanSwagger2;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Description
 *
 * @author fosin
 */
@SpringCloudApplication
@EnableZuulProxy //启用RateLimit
@EnableHystrixDashboard
@EnableAnanSwagger2
@EnableAnanResourceServer
@EnableWebSecurity
//@EnableTurbineStream
@EnableAnanRedis
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulGatewayApplication.class)
                .banner(new AnanBanner("AnAn Zuul Gateway"))
                .logStartupInfo(true)
                .run(args);
    }
}
