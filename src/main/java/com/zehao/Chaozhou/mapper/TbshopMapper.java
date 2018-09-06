package com.zehao.Chaozhou.mapper;

import com.zehao.Chaozhou.pojo.Tbshop;
import com.zehao.Chaozhou.pojo.TbshopExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbshopMapper {
    int countByExample(TbshopExample example);

    int deleteByExample(TbshopExample example);

    int deleteByPrimaryKey(String shossthhId);

    int insert(Tbshop record);

    int insertSelective(Tbshop record);

    List<Tbshop> selectByExampleWithBLOBs(TbshopExample example);

    List<Tbshop> selectByExample(TbshopExample example);

    Tbshop selectByPrimaryKey(String shossthhId);

    int updateByExampleSelective(@Param("record") Tbshop record, @Param("example") TbshopExample example);

    int updateByExampleWithBLOBs(@Param("record") Tbshop record, @Param("example") TbshopExample example);

    int updateByExample(@Param("record") Tbshop record, @Param("example") TbshopExample example);

    int updateByPrimaryKeySelective(Tbshop record);

    int updateByPrimaryKeyWithBLOBs(Tbshop record);

    int updateByPrimaryKey(Tbshop record);
}