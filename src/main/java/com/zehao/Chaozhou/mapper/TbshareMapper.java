package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbshare;
import com.zehao.Chaozhou.pojo.TbshareExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbshareMapper {
    int countByExample(TbshareExample example);

    int deleteByExample(TbshareExample example);

    int deleteByPrimaryKey(String shassthhId);

    int insert(Tbshare record);

    int insertSelective(Tbshare record);

    List<Tbshare> selectByExampleWithBLOBs(TbshareExample example);

    List<Tbshare> selectByExample(TbshareExample example);

    Tbshare selectByPrimaryKey(String shassthhId);

    int updateByExampleSelective(@Param("record") Tbshare record, @Param("example") TbshareExample example);

    int updateByExampleWithBLOBs(@Param("record") Tbshare record, @Param("example") TbshareExample example);

    int updateByExample(@Param("record") Tbshare record, @Param("example") TbshareExample example);

    int updateByPrimaryKeySelective(Tbshare record);

    int updateByPrimaryKeyWithBLOBs(Tbshare record);

    int updateByPrimaryKey(Tbshare record);
}