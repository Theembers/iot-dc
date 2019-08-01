package com.theembers.iot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-19 13:12
 */
@SpringBootApplication(scanBasePackages = "com.theembers.iot")
public class IotDCApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .banner(new TheEmbersBanner())
                .bannerMode(Banner.Mode.LOG)
                .sources(IotDCApplication.class)
                .run(args);
    }
}
