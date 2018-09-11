package com.zehao.Chaozhou.service;

import com.zehao.Chaozhou.param.UserQueryParam;


/**
 * Created by MS on 2018/8/20.
 */
public interface UserService {

    String queryAllUser(UserQueryParam userQueryParam);

    String deleteUserById(String id);
}
