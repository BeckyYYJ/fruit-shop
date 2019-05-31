package com.bb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by becky_yyj
 */
@Controller
public class CommoditiesController extends BaseController{
    @RequestMapping("/commodities/list.action")
    public String list(){
        return "/commoditiesHome.jsp";//跳转货物信息页面
    }
}
