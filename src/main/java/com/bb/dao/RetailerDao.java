package com.bb.dao;

import com.bb.entity.Retailer;

import java.util.Map;

/**
 * created by becky_yyj
 */
public interface RetailerDao extends BaseDao<Retailer>  {
    public int count(Map map);
}
