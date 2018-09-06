package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbinformation;
import com.zehao.Chaozhou.pojo.TbinformationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbinformationMapper {
    int countByExample(TbinformationExample example);

    int deleteByExample(TbinformationExample example);

    int deleteByPrimaryKey(Long intid);

    int insert(Tbinformation record);

    int insertSelective(Tbinformation record);

    List<Tbinformation> selectByExampleWithBLOBs(TbinformationExample example);

    List<Tbinformation> selectByExample(TbinformationExample example);

    Tbinformation selectByPrimaryKey(Long intid);

    int updateByExampleSelective(@Param("record") Tbinformation record, @Param("example") TbinformationExample example);

    int updateByExampleWithBLOBs(@Param("record") Tbinformation record, @Param("example") TbinformationExample example);

    int updateByExample(@Param("record") Tbinformation record, @Param("example") TbinformationExample example);

    int updateByPrimaryKeySelective(Tbinformation record);

    int updateByPrimaryKeyWithBLOBs(Tbinformation record);

    int updateByPrimaryKey(Tbinformation record);
}