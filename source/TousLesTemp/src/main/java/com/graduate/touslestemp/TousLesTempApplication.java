package com.graduate.touslestemp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @File: TousLesTempApplication.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:53 AM
 * @Update: 21/6/2023
 */
@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@EnableJpaRepositories
public class TousLesTempApplication {

    public static void main(String[] args) {
        SpringApplication.run(TousLesTempApplication.class, args);
    }

}
