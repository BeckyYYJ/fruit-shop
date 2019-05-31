package com.bb.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by becky_yyj
 */
public abstract class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
    }
}
