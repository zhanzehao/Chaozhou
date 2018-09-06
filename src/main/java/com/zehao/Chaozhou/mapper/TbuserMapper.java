package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbuser;
import com.zehao.Chaozhou.pojo.TbuserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbuserMapper {
    int countByExample(TbuserExample example);

    int deleteByExample(TbuserExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(Tbuser record);

    int insertSelective(Tbuser record);

    List<Tbuser> selectByExample(TbuserExample example);

    Tbuser selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") Tbuser record, @Param("example") TbuserExample example);

    int updateByExample(@Param("record") Tbuser record, @Param("example") TbuserExample example);

    int updateByPrimaryKeySelective(Tbuser record);

    int updateByPrimaryKey(Tbuser record);
}