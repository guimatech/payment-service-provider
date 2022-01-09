package io.github.guimatech.cleanarchitecture.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class WebConfiguration {
}
