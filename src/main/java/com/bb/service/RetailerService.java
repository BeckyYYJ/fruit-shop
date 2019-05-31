package com.bb.service;

import com.bb.entity.Retailer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * created by becky_yyj
 */
public interface RetailerService {
    public Retailer get(Serializable id);
    public List<Retailer> find(Map map);
    public void insert(Retailer retailer);
    public void update(Retailer retailer);
    public void deleteById(Serializable id);
    public void delete(Serializable[] ids);
    public int count(Map map);
}
