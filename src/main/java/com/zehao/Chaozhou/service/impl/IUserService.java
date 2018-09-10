package com.zehao.Chaozhou.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zehao.Chaozhou.mapper.TbuserMapper;
import com.zehao.Chaozhou.param.UserQueryParam;
import com.zehao.Chaozhou.pojo.Tbuser;
import com.zehao.Chaozhou.pojo.TbuserExample;
import com.zehao.Chaozhou.service.UserService;
import com.zehao.Chaozhou.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class IUserService implements UserService {

    @Autowired
    private TbuserMapper tbuserMapper;

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

        Gson gson = new GsonBuilder().serializeNulls().create();

        Map<Object,Object> info = new HashMap<>();
        info.put("pageData",tbuserList);
        info.put("total",total);
        info.put("draw",draw);

        return gson.toJson(info);
    }
}
