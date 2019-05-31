package com.bb.service;

import com.bb.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * created by becky_yyj
 */
public interface UserService {
    public User get(Serializable id);
    public List<User> find(Map map);
    public void insert(User entity);
    public void update(User entity);
    public void deleteById(Serializable id);
    public void delete(Serializable[] ids);
}
