package com.bb.service.impl;

import com.bb.dao.RetailerDao;
import com.bb.entity.Retailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * created by becky_yyj
 */
@Service
public class RetailerServiceImpl implements com.bb.service.RetailerService {
    @Autowired
    RetailerDao retailerDao;

    @Override
    public Retailer get(Serializable id) {
        return retailerDao.get(id);
    }

    @Override
    public List<Retailer> find(Map map) {
        return retailerDao.find(map);
    }

    @Override
    public void insert(Retailer retailer) {
        retailerDao.insert(retailer);
    }

    @Override
    public void update(Retailer retailer) {
        retailerDao.update(retailer);
    }

    @Override
    public void deleteById(Serializable id) {
        retailerDao.deleteById(id);
    }

    @Override
    public void delete(Serializable[] ids) {
        retailerDao.delete(ids);
    }

    @Override
    public int count(Map map) {
        return retailerDao.count(map);
    }
}
