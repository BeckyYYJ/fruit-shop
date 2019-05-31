package com.bb.dao.impl;

import com.bb.dao.BaseDao;
import com.bb.dao.UserDao;
import com.bb.entity.User;
import org.springframework.stereotype.Repository;

/**
 * created by becky_yyj
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    public UserDaoImpl(){
        //设置命名空间
        super.setNs("com.bb.mapper.UserMapper");
    }
}
