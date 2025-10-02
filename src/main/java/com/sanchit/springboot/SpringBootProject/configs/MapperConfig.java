package com.sanchit.springboot.SpringBootProject.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Model-Mapper Config Set here
 * */
@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
