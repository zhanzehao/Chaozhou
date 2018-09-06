package com.zehao.Chaozhou.controller;

import com.zehao.Chaozhou.param.PageParam;
import com.zehao.Chaozhou.service.UserService;
import com.zehao.Chaozhou.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.QueryParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/displayAllUser",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryAllUser(@RequestBody PageParam pageParam) {
        return userService.queryAllUser(pageParam);
    }

}
