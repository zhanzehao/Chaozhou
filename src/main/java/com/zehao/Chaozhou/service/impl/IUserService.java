package com.zehao.Chaozhou.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zehao.Chaozhou.common.GsonSingleton;
import com.zehao.Chaozhou.mapper.TbuserMapper;
import com.zehao.Chaozhou.common.Vo.UserQueryParam;
import com.zehao.Chaozhou.pojo.Tbuser;
import com.zehao.Chaozhou.pojo.TbuserExample;
import com.zehao.Chaozhou.service.UserService;
import com.zehao.Chaozhou.utils.COSUtil;
import com.zehao.Chaozhou.utils.Logger;
import com.zehao.Chaozhou.utils.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class IUserService implements UserService {

    @Autowired
    private TbuserMapper tbuserMapper;

    private Gson gson = GsonSingleton.getInstance();

    @Override
    public String queryAllUser(UserQueryParam userQueryParam) {

        String draw = userQueryParam.getDraw();
        String startIndex = userQueryParam.getStartIndex();
        String pageSize = userQueryParam.getPageSize();
        String total;

        String orderColumn = userQueryParam.getOrderColumn();
        if (orderColumn == null) orderColumn = "uid";
        if (orderColumn.equals("phoneNumber")) orderColumn = "phone_number";

        String orderDir = userQueryParam.getOrderDir();
        if (orderDir == null) orderDir = "asc";

        TbuserExample tbuserExample = new TbuserExample();
        TbuserExample.Criteria criteria = tbuserExample.createCriteria();

        String fuzzy = userQueryParam.getFuzzySearch();
        if("true".equals(fuzzy)){
            String searchValue = userQueryParam.getFuzzy();
            if (searchValue!=null&&!searchValue.equals("")) {
                tbuserExample.or().andUnameLike("%"+searchValue+"%");
                tbuserExample.or().andPhoneNumberLike("%"+searchValue+"%");
                tbuserExample.or().andSexLike("%"+searchValue+"%");
            }
        }else {
            String uname = userQueryParam.getUname();
            if (uname!=null&&!uname.equals("")) {
                criteria.andUnameLike("%"+uname+"%");
            }
            String phoneNumber = userQueryParam.getPhoneNumber();
            Logger.info(phoneNumber);
            if (phoneNumber!=null&&!phoneNumber.equals("")) {
                criteria.andPhoneNumberLike("%"+phoneNumber+"%");
            }
            String sex = userQueryParam.getSex();
            if (sex!=null&&!sex.equals("")) {
                criteria.andSexLike("%"+sex+"%");
            }
        }

        tbuserExample.setOrderByClause(orderColumn+" "+orderDir);
        tbuserExample.setOffset(startIndex);
        tbuserExample.setLimit(pageSize);

        List<Tbuser> tbuserList = tbuserMapper.selectByExample(tbuserExample);
        total = String.valueOf(tbuserMapper.countByExample(tbuserExample));

        Map<Object,Object> info = new HashMap<>();
        info.put("pageData",tbuserList);
        info.put("total",total);
        info.put("draw",draw);

        return gson.toJson(info);
    }

    @Override
    public String queryUserById(String id) {
        Tbuser tbuser = tbuserMapper.selectByPrimaryKey(Long.valueOf(id));
        Logger.info(tbuser.toString());

        Map<Object,Object> info = new HashMap<>();
        info.put("user",tbuser);

        return gson.toJson(info);
    }

    @Override
    public String insertUser(Tbuser tbuser, MultipartFile file) throws IOException {
        /*
            String upload = COSUtil.upload(file, Picture.USERHEAD);
            JsonElement je = new JsonParser().parse(upload);
            JsonElement imgstatus = je.getAsJsonObject().get("status");
            if(imgstatus != null && imgstatus.getAsLong() == 0) {
                JsonElement data = je.getAsJsonObject().get("data");
                if(data != null && data.getAsString() != null) {
                    Logger.info(data.getAsString());
                    tbuser.setHead(data.getAsString());
                }
            }
        */
        if (file != null) {
            String head = COSUtil.upload(file,Picture.USERHEAD);
            Logger.info(head);
            tbuser.setHead(head);
        }
        int result = tbuserMapper.insertSelective(tbuser);

        String status;
        if (result == 0) {
            status = "fault";
        } else {
            status = "success";
        }

        Map<Object,Object> info = new HashMap<>();
        info.put("status",status);

        return gson.toJson(info);
    }

    @Override
    public String updateUserById(Tbuser tbuser, MultipartFile file) throws IOException {
        /*
        String upload = COSUtil.upload(file,Picture.USERHEAD);
        JsonElement je = new JsonParser().parse(upload);
        JsonElement imgstatus = je.getAsJsonObject().get("status");
        if(imgstatus != null && imgstatus.getAsLong() == 0) {
            JsonElement data = je.getAsJsonObject().get("data");
            if(data != null && data.getAsString() != null) {
                tbuser.setHead(data.getAsString());
            }
        }*/
        if (!file.isEmpty()) {
            String head = COSUtil.upload(file,Picture.USERHEAD);
            Logger.info(head);
            tbuser.setHead(head);
        }

        int result = tbuserMapper.updateByPrimaryKeySelective(tbuser);
        String status;
        if (result == 0) {
            status = "fault";
        } else {
            status = "success";
        }

        Map<Object,Object> info = new HashMap<>();
        info.put("status",status);

        return gson.toJson(info);
    }

    @Override
    public String deleteUserById(String[] id) {
        String status = "fault";

        for (String eachid : id) {
            int result = tbuserMapper.deleteByPrimaryKey(Long.valueOf(eachid));
            if (result == 0) {
                status = "fault";
            } else {
                status = "success";
            }
        }

        Map<Object,Object> info = new HashMap<>();
        info.put("status",status);

        return gson.toJson(info);
    }
}
