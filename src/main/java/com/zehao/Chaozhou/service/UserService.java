package com.zehao.Chaozhou.service;

import com.zehao.Chaozhou.param.UserQueryParam;
import com.zehao.Chaozhou.pojo.Tbuser;


/**
 * Created by MS on 2018/8/20.
 */
public interface UserService {

    String queryAllUser(UserQueryParam userQueryParam);

    String queryUserById(String id);

    String insertUser(Tbuser tbuser);

    String updateUserById(Tbuser tbuser);

    String deleteUserById(String[] id);
}
