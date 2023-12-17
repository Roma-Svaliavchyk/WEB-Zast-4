package com.roma_s.pw4;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.roma_s.pw4.controller",
                               "com.roma_s.pw4.repository",
                               "com.roma_s.pw4.service"})

@EnableJpaRepositories(basePackages = "com.roma_s.pw4.repository")

public class AppConfig { }
