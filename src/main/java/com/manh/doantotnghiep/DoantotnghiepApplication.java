package com.manh.doantotnghiep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.manh.doantotnghiep.service.ImageStorageService;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableConfigurationProperties
public class DoantotnghiepApplication implements CommandLineRunner{
    @Autowired
    private ImageStorageService imageStorageService;
    public static void main(String[] args) {
        SpringApplication.run(DoantotnghiepApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        imageStorageService.init();
        
    }
}
