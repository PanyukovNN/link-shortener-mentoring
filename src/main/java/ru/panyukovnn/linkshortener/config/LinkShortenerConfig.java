package ru.panyukovnn.linkshortener.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
public class LinkShortenerConfig {

    @Bean
    public String notFoundPage() throws IOException {
        File file = ResourceUtils.getFile("classpath:templates/404.html");

        return Files.readString(file.toPath());
    }
}
