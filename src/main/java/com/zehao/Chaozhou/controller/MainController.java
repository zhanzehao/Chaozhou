package com.zehao.Chaozhou.controller;

import com.zehao.Chaozhou.param.UserQueryParam;
import com.zehao.Chaozhou.pojo.Tbuser;
import com.zehao.Chaozhou.service.UserService;
import com.zehao.Chaozhou.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/displayAllUser",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryAllUser(@RequestBody UserQueryParam userQueryParam) {
        return userService.queryAllUser(userQueryParam);
    }

    @RequestMapping(value = "queryUserById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryUserById(@RequestParam String id) {
        return userService.queryUserById(id);
    }

    @RequestMapping(value = "updateUserById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateUserById(@RequestBody Tbuser tbuser) {
        return userService.updateUserById(tbuser);
    }

    @RequestMapping(value = "/deleteUserById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteUserById(@RequestParam String id) {
        return userService.deleteUserById(id);
    }
}
