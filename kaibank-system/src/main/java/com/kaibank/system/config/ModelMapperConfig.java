package com.kaibank.system.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The ModelMapperConfig class is used to define configurations for ModelMapper
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class ModelMapperConfig {

  /**
   * Creating a ModelMapper bean to be managed by the Spring container.
   *
   * @author Pabasara
   * @return instance of ModelMapper
   * @since 1.0
   */
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    return modelMapper;
  }
}
