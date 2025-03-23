package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务类
 * 提供用户注册、登录、查询等功能
 */
@Service
public class UserService {
    private final UserDao userDao;

    /**
     * 构造函数注入UserDao依赖
     * 
     * @param userDao 用户数据访问对象
     */
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 用户注册
     * 
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @return 注册成功的用户对象
     * @throws RuntimeException 如果用户名已存在
     */
    public User register(String username, String password, String email) {
        // 检查用户名是否已存在
        if (userDao.findByUsername(username) != null) {
            throw new RuntimeException("用户名已存在");
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        
        return userDao.save(user);
    }

    /**
     * 用户登录
     * 
     * @param username 用户名
     * @param password 密码
     * @return 登录成功的用户对象
     * @throws RuntimeException 如果用户不存在或密码错误
     */
    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }
        
        return user;
    }

    /**
     * 根据ID获取用户
     * 
     * @param id 用户ID
     * @return 用户对象
     * @throws RuntimeException 如果用户不存在
     */
    public User getUserById(Long id) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    /**
     * 获取所有用户
     * 
     * @return 用户列表
     */
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    /**
     * 删除用户
     * 
     * @param id 用户ID
     */
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
} 