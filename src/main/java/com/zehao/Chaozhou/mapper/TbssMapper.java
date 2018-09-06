package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbss;
import com.zehao.Chaozhou.pojo.TbssExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbssMapper {
    int countByExample(TbssExample example);

    int deleteByExample(TbssExample example);

    int deleteByPrimaryKey(Long ssid);

    int insert(Tbss record);

    int insertSelective(Tbss record);

    List<Tbss> selectByExample(TbssExample example);

    Tbss selectByPrimaryKey(Long ssid);

    int updateByExampleSelective(@Param("record") Tbss record, @Param("example") TbssExample example);

    int updateByExample(@Param("record") Tbss record, @Param("example") TbssExample example);

    int updateByPrimaryKeySelective(Tbss record);

    int updateByPrimaryKey(Tbss record);
}