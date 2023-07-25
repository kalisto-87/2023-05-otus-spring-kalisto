package ru.otus.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({TestProps.class, AppProps.class})
public class ApplicationConfig {

}
