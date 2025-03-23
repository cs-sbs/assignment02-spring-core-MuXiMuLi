package org.example.dao;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    // 模拟数据库
    private final Map<Long, User> userMap = new HashMap<>();
    private Long idCounter = 1L;

    public UserDao() {
        // 初始化一些测试数据
        User user1 = new User(idCounter++, "admin", "admin123", "admin@example.com");
        User user2 = new User(idCounter++, "user1", "password1", "user1@example.com");
        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idCounter++);
        }
        userMap.put(user.getId(), user);
        return user;
    }

    public User findById(Long id) {
        return userMap.get(id);
    }

    public User findByUsername(String username) {
        return userMap.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    public void delete(Long id) {
        userMap.remove(id);
    }
} 