package com.zehao.Chaozhou.service;

import com.zehao.Chaozhou.common.Vo.UserQueryParam;
import com.zehao.Chaozhou.pojo.Tbuser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * Created by MS on 2018/8/20.
 */
public interface UserService {

    String queryAllUser(UserQueryParam userQueryParam);

    String queryUserById(String id);

    String insertUser(Tbuser tbuser, MultipartFile file) throws IOException;

    String updateUserById(Tbuser tbuser, MultipartFile file) throws IOException;

    String deleteUserById(String[] id);
}
