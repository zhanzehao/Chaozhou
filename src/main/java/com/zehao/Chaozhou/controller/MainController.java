package com.zehao.Chaozhou.controller;

import com.zehao.Chaozhou.common.Vo.UserQueryParam;
import com.zehao.Chaozhou.pojo.Tbuser;
import com.zehao.Chaozhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;


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

    @RequestMapping(value = "insertUser", method= RequestMethod.POST, produces="application/json; charset=utf-8")
    @ResponseBody
    public String insertUser(@RequestParam(value = "file", required=false) MultipartFile file,
                             MultipartHttpServletRequest request) throws IOException {
        Tbuser tbuser = new Tbuser();
        tbuser.setUname(request.getParameter("uname"));
        tbuser.setPhoneNumber(request.getParameter("phoneNumber"));
        tbuser.setSex(request.getParameter("sex"));
        tbuser.setPassword(request.getParameter("password"));
        tbuser.setBirthday(request.getParameter("birthday"));
        return userService.insertUser(tbuser, file);
    }

    @RequestMapping(value = "updateUserById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateUserById(@RequestParam(value = "file", required=false) MultipartFile file,
                                 MultipartHttpServletRequest request) throws IOException {
        Tbuser tbuser = new Tbuser();
        tbuser.setUid(Long.valueOf(request.getParameter("uid")));
        tbuser.setUname(request.getParameter("uname"));
        tbuser.setPhoneNumber(request.getParameter("phoneNumber"));
        tbuser.setSex(request.getParameter("sex"));
        tbuser.setPassword(request.getParameter("password"));
        tbuser.setBirthday(request.getParameter("birthday"));
        return userService.updateUserById(tbuser, file);
    }

    @RequestMapping(value = "/deleteUserById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteUserById(@RequestParam String[] id) {
        return userService.deleteUserById(id);
    }
}
