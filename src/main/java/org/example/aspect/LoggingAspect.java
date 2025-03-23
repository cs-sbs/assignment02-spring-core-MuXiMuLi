package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 日志记录切面
 * 使用AOP实现对服务层方法的调用日志记录、执行时间统计和异常处理
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * 定义切点：拦截service包下所有类的所有方法
     */
    @Pointcut("execution(* org.example.service.*.*(..))")
    public void serviceMethods() {}

    /**
     * 环绕通知：在目标方法执行前后添加自定义行为
     * 
     * @param joinPoint 连接点，包含目标方法的信息
     * @return 目标方法的返回值
     * @throws Throwable 如果目标方法抛出异常
     */
    @Around("serviceMethods()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法信息
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        
        // 记录方法开始执行的日志
        System.out.println("=== 开始执行 " + className + "." + methodName + 
                " 方法，参数: " + Arrays.toString(args) + " ===");
        
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            // 执行目标方法
            Object result = joinPoint.proceed();
            // 计算执行时间
            long endTime = System.currentTimeMillis();
            
            // 记录方法执行成功的日志
            System.out.println("=== " + className + "." + methodName + 
                    " 方法执行完成，耗时: " + (endTime - startTime) + "ms, 返回值: " + result + " ===");
            
            return result;
        } catch (Throwable e) {
            // 记录方法执行异常的日志
            System.out.println("=== " + className + "." + methodName + 
                    " 方法执行异常: " + e.getMessage() + " ===");
            throw e;
        }
    }
} 