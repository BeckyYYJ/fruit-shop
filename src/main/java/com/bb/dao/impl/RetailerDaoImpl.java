package com.bb.dao.impl;

import com.bb.dao.RetailerDao;
import com.bb.entity.Retailer;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * created by becky_yyj
 */
@Repository
public class RetailerDaoImpl extends  BaseDaoImpl<Retailer> implements RetailerDao {
    RetailerDaoImpl(){
        setNs("com.bb.mapper.RetailerMapper");
    }
    public int count(Map map){
        return this.getSqlSession().selectOne(this.getNs()+".count",map);
    }
}
