package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbmap;
import com.zehao.Chaozhou.pojo.TbmapExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbmapMapper {
    int countByExample(TbmapExample example);

    int deleteByExample(TbmapExample example);

    int deleteByPrimaryKey(Long mid);

    int insert(Tbmap record);

    int insertSelective(Tbmap record);

    List<Tbmap> selectByExample(TbmapExample example);

    Tbmap selectByPrimaryKey(Long mid);

    int updateByExampleSelective(@Param("record") Tbmap record, @Param("example") TbmapExample example);

    int updateByExample(@Param("record") Tbmap record, @Param("example") TbmapExample example);

    int updateByPrimaryKeySelective(Tbmap record);

    int updateByPrimaryKey(Tbmap record);
}