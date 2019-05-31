package com.bb.controller;

import com.bb.entity.Retailer;
import com.bb.service.RetailerService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * created by becky_yyj
 */
@Controller
public class RetailerController extends BaseController {
    @Resource
    RetailerService retailerService;

    @RequestMapping("/retailer/list.action")
    public String toRetailList(Model model, Retailer retailer,String startTime,String endTime){
        Map<String,Object> map=this.retailerToMap(retailer);
        if(startTime!=null&&!startTime.equals("")){
            map.put("startTime",startTime);
        }
        if(endTime!=null&&!endTime.equals("")){
            map.put("endTime",endTime);
        }
        model.addAttribute("currentPage",retailer.getCurrentPage());
        model.addAttribute("startPage",retailer.getCurrentPage());
        int countNumber = retailerService.count(map);
        model.addAttribute("countNumber",countNumber);
        int pageSize = retailer.getPageSize();
        model.addAttribute("pageSize",pageSize);
        int sumPageNumber=countNumber%pageSize==0?(countNumber/pageSize):((countNumber/pageSize)+1);
        model.addAttribute("sumPageNumber",sumPageNumber);

        return "/retailer/retailerHome.jsp";
    }
    private Map<String, Object> retailerToMap(Retailer retailer){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name",checkStringIsEmpty(retailer.getName()));
        map.put("telephone",checkStringIsEmpty(retailer.getTelephone()));
        map.put("address",checkStringIsEmpty(retailer.getAddress()));
        map.put("status",retailer.getStatus()==-1?null:retailer.getStatus());
        map.put("createTime",checkStringIsEmpty(retailer.getCreateTime()));
        map.put("startPage",retailer.getStartPage());
        map.put("pageSize",retailer.getPageSize());
        return map;
    }
    private String checkStringIsEmpty(String param){
        return param==null?null:(param.equals("")?null:"%"+param+"%");
    }
   @RequestMapping("/retailer/add.action")
    public String add(Model model,Retailer retailer){
        retailer.setRetailerId(UUID.randomUUID().toString());
        retailer.setCreateTime(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
        retailerService.insert(retailer);
        //构建新的列表查询条件，只要status属性
       Retailer queryRetailer = new Retailer();
       queryRetailer.setStatus(-1);
        return toRetailList(model,queryRetailer,null,null);
    }
}
