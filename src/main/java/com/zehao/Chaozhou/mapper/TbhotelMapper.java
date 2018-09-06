package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbhotel;
import com.zehao.Chaozhou.pojo.TbhotelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbhotelMapper {
    int countByExample(TbhotelExample example);

    int deleteByExample(TbhotelExample example);

    int deleteByPrimaryKey(String hotssthhId);

    int insert(Tbhotel record);

    int insertSelective(Tbhotel record);

    List<Tbhotel> selectByExampleWithBLOBs(TbhotelExample example);

    List<Tbhotel> selectByExample(TbhotelExample example);

    Tbhotel selectByPrimaryKey(String hotssthhId);

    int updateByExampleSelective(@Param("record") Tbhotel record, @Param("example") TbhotelExample example);

    int updateByExampleWithBLOBs(@Param("record") Tbhotel record, @Param("example") TbhotelExample example);

    int updateByExample(@Param("record") Tbhotel record, @Param("example") TbhotelExample example);

    int updateByPrimaryKeySelective(Tbhotel record);

    int updateByPrimaryKeyWithBLOBs(Tbhotel record);

    int updateByPrimaryKey(Tbhotel record);
}