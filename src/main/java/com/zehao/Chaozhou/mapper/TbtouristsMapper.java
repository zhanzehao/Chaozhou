package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbtourists;
import com.zehao.Chaozhou.pojo.TbtouristsExample;
import com.zehao.Chaozhou.pojo.TbtouristsWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbtouristsMapper {
    int countByExample(TbtouristsExample example);

    int deleteByExample(TbtouristsExample example);

    int deleteByPrimaryKey(String toussthhId);

    int insert(TbtouristsWithBLOBs record);

    int insertSelective(TbtouristsWithBLOBs record);

    List<TbtouristsWithBLOBs> selectByExampleWithBLOBs(TbtouristsExample example);

    List<Tbtourists> selectByExample(TbtouristsExample example);

    TbtouristsWithBLOBs selectByPrimaryKey(String toussthhId);

    int updateByExampleSelective(@Param("record") TbtouristsWithBLOBs record, @Param("example") TbtouristsExample example);

    int updateByExampleWithBLOBs(@Param("record") TbtouristsWithBLOBs record, @Param("example") TbtouristsExample example);

    int updateByExample(@Param("record") Tbtourists record, @Param("example") TbtouristsExample example);

    int updateByPrimaryKeySelective(TbtouristsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TbtouristsWithBLOBs record);

    int updateByPrimaryKey(Tbtourists record);
}