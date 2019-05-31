package com.bb.dao;

import com.bb.entity.User;

/**
 * created by becky_yyj
 */
public interface UserDao extends BaseDao<User>{
    //这里可以直接使用继承的BaseDao的增删改查
    //之后还可以再添加新的方法定义
}
