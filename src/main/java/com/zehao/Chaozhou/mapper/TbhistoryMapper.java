package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbhistory;
import com.zehao.Chaozhou.pojo.TbhistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbhistoryMapper {
    int countByExample(TbhistoryExample example);

    int deleteByExample(TbhistoryExample example);

    int deleteByPrimaryKey(String hisssthhId);

    int insert(Tbhistory record);

    int insertSelective(Tbhistory record);

    List<Tbhistory> selectByExampleWithBLOBs(TbhistoryExample example);

    List<Tbhistory> selectByExample(TbhistoryExample example);

    Tbhistory selectByPrimaryKey(String hisssthhId);

    int updateByExampleSelective(@Param("record") Tbhistory record, @Param("example") TbhistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") Tbhistory record, @Param("example") TbhistoryExample example);

    int updateByExample(@Param("record") Tbhistory record, @Param("example") TbhistoryExample example);

    int updateByPrimaryKeySelective(Tbhistory record);

    int updateByPrimaryKeyWithBLOBs(Tbhistory record);

    int updateByPrimaryKey(Tbhistory record);
}