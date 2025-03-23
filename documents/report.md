# Spring IoC和AOP实践报告

## 应用场景

本项目实现了一个简单的用户服务系统，包含用户注册、登录和查询功能。使用Spring的IoC容器管理各组件，并通过AOP实现了方法执行时间日志记录和异常处理。

## 技术实现

### IoC实现方式

采用注解方式实现Bean装配：
- 使用`@Repository`注解标记数据访问层组件
- 使用`@Service`注解标记业务逻辑层组件
- 使用`@Component`和`@Aspect`注解标记切面组件
- 使用`@Autowired`进行依赖注入

### AOP实现方式

采用注解方式实现AOP：
- 使用`@Aspect`注解定义切面
- 使用`@Pointcut`定义切点
- 使用`@Around`定义环绕通知，实现方法执行时间记录和异常处理

## 运行结果

运行Main类后，可以看到以下输出： 
![](\documents\1.png)