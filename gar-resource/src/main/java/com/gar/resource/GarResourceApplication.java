package com.gar.resource;

import static com.gar.resource.constants.GarConstants.BASE_PACKAGE_NAME;
import static com.gar.resource.constants.GarConstants.DAO_PACKAGE_NAME;
import static com.gar.resource.constants.GarConstants.MODEL_PACKAGE_NAME;

import java.util.UUID;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@RestController
@EnableResourceServer
@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
@Configuration
@EnableEncryptableProperties
@EnableScheduling
@EnableBatchProcessing
@EnableJpaRepositories(basePackages = { DAO_PACKAGE_NAME })
@EntityScan(basePackages = { MODEL_PACKAGE_NAME })
@ComponentScan(BASE_PACKAGE_NAME)
@EnableAutoConfiguration(exclude = { MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class })
public class GarResourceApplication {

    @RequestMapping("/")
    public Message home() {
	return new Message("Hello World");
    }

    public static void main(String[] args) {
	SpringApplication.run(GarResourceApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
}

class Message {
    private String id = UUID.randomUUID().toString();
    private String content;

    Message() {
    }

    public Message(String content) {
	this.content = content;
    }

    public String getId() {
	return id;
    }

    public String getContent() {
	return content;
    }
}
