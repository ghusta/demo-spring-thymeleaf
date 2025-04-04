package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = com.example.RootPackage.class)
public class AppConfig {
    // Ici, on peut ajouter des Beans (ex: DataSource, Services...)

}
