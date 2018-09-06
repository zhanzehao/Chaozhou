package com.zehao.Chaozhou.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zehao.Chaozhou.mapper.TbuserMapper;
import com.zehao.Chaozhou.param.PageParam;
import com.zehao.Chaozhou.pojo.Tbuser;
import com.zehao.Chaozhou.pojo.TbuserExample;
import com.zehao.Chaozhou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class IUserService implements UserService {

    @Autowired
    private TbuserMapper tbuserMapper;

    @Override
    public String queryAllUser(PageParam pageParam) {

        String draw = pageParam.getDraw();
        String startIndex = pageParam.getStartIndex();
        String pageSize = pageParam.getPageSize();
        String total;

        String orderColumn = pageParam.getOrderColumn();
        if (orderColumn == null) orderColumn = "uid";

        String orderDir = pageParam.getOrderDir();
        if (orderDir == null) orderDir = "asc";

        TbuserExample tbuserExample = new TbuserExample();

        tbuserExample.setOrderByClause(orderColumn+" "+orderDir);
        tbuserExample.setOffset(startIndex);
        tbuserExample.setLimit(pageSize);

        List<Tbuser> tbuserList = tbuserMapper.selectByExample(tbuserExample);
        total = String.valueOf(tbuserMapper.selectByExample(null).size());

        Gson gson = new GsonBuilder().serializeNulls().create();

        Map<Object,Object> info = new HashMap<>();
        info.put("pageData",tbuserList);
        info.put("total",total);
        info.put("draw",draw);

        return gson.toJson(info);
    }
}
