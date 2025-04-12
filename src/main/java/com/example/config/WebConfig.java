package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.nio.charset.StandardCharsets;

@Configuration(proxyBeanMethods = false)
@EnableWebMvc
@ComponentScan(basePackages = "com.example.controller")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/icon/**")
                .addResourceLocations("/icon/");

        // This tells Spring where to find WebJar static resources
        // For version agnostic WebJars, see :
        // - org.springframework.web.servlet.config.annotation.ResourceChainRegistration.isWebJarsAssetLocatorPresent
        // - org.springframework.web.servlet.resource.WebJarsResourceResolver (depends on webjars-locator)
        // As of Spring 6.2, use : org.springframework.web.servlet.resource.LiteWebJarsResourceResolver (depends on webjars-locator-lite)
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(true);
    }

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        resolver.setOrder(1);
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        // ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }
}
