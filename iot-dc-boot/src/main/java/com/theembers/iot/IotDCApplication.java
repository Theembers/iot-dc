package com.theembers.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-10-19 13:12
 */
@SpringBootApplication(scanBasePackages="com.theembers.iot")
public class IotDCApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotDCApplication.class, args);
    }
}
