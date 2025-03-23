package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring配置类
 * 启用组件扫描和AOP功能
 */
@Configuration
@ComponentScan(basePackages = "org.example")  // 指定要扫描的包
@EnableAspectJAutoProxy  // 启用AspectJ自动代理
public class AppConfig {
    // Spring配置类，启用组件扫描和AOP
} 