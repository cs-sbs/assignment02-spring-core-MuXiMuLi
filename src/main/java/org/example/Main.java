package org.example;

import org.example.config.AppConfig;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 主程序入口类
 * 用于测试Spring IoC和AOP功能
 */
public class Main {
    public static void main(String[] args) {


        // 初始化Spring容器
        AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(AppConfig.class);
        
        // 获取UserService Bean
        UserService userService = context.getBean(UserService.class);
        
        System.out.println("\n===== 测试用户服务 =====\n");
        
        // 测试获取所有用户
        System.out.println("获取所有用户:");
        userService.getAllUsers().forEach(System.out::println);
        
        // 测试用户注册
        System.out.println("\n注册新用户:");
        User newUser = userService.register("testuser", "password123", "test@example.com");
        System.out.println("新用户注册成功: " + newUser);
        
        // 测试用户登录
        System.out.println("\n用户登录:");
        User loggedInUser = userService.login("testuser", "password123");
        System.out.println("登录成功: " + loggedInUser);
        
        // 测试获取用户
        System.out.println("\n获取用户信息:");
        User user = userService.getUserById(1L);
        System.out.println("获取到用户: " + user);
        
        // 测试异常情况
        try {
            System.out.println("\n测试异常情况 - 登录失败:");
            userService.login("nonexistent", "wrongpassword");
        } catch (Exception e) {
            System.out.println("预期的异常: " + e.getMessage());
        }


        // 关闭Spring容器
        context.close();
    }
}