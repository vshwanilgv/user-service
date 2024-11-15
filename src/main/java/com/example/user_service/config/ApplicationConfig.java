package com.example.user_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Configuration;
import com.example.user_service.dto.UserDTO;
import com.example.user_service.model.User;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
