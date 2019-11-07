package com.theembers.iot;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author TheEmbers Guo
 * createTime 2019-11-07 13:45
 */
@SpringBootApplication(scanBasePackages = "com.theembers.iot")
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .banner(new TheEmbersBanner())
                .bannerMode(Banner.Mode.LOG)
                .sources(Application.class)
                .run(args);
    }
}
