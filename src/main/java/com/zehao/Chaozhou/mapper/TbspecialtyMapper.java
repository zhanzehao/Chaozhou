package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbspecialty;
import com.zehao.Chaozhou.pojo.TbspecialtyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbspecialtyMapper {
    int countByExample(TbspecialtyExample example);

    int deleteByExample(TbspecialtyExample example);

    int deleteByPrimaryKey(String spessthhId);

    int insert(Tbspecialty record);

    int insertSelective(Tbspecialty record);

    List<Tbspecialty> selectByExampleWithBLOBs(TbspecialtyExample example);

    List<Tbspecialty> selectByExample(TbspecialtyExample example);

    Tbspecialty selectByPrimaryKey(String spessthhId);

    int updateByExampleSelective(@Param("record") Tbspecialty record, @Param("example") TbspecialtyExample example);

    int updateByExampleWithBLOBs(@Param("record") Tbspecialty record, @Param("example") TbspecialtyExample example);

    int updateByExample(@Param("record") Tbspecialty record, @Param("example") TbspecialtyExample example);

    int updateByPrimaryKeySelective(Tbspecialty record);

    int updateByPrimaryKeyWithBLOBs(Tbspecialty record);

    int updateByPrimaryKey(Tbspecialty record);
}