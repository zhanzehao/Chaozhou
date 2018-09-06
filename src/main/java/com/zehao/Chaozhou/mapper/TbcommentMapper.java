package com.zehao.Chaozhou.mapper;


import com.zehao.Chaozhou.pojo.Tbcomment;
import com.zehao.Chaozhou.pojo.TbcommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbcommentMapper {
    int countByExample(TbcommentExample example);

    int deleteByExample(TbcommentExample example);

    int deleteByPrimaryKey(Long cid);

    int insert(Tbcomment record);

    int insertSelective(Tbcomment record);

    List<Tbcomment> selectByExampleWithBLOBs(TbcommentExample example);

    List<Tbcomment> selectByExample(TbcommentExample example);

    Tbcomment selectByPrimaryKey(Long cid);

    int updateByExampleSelective(@Param("record") Tbcomment record, @Param("example") TbcommentExample example);

    int updateByExampleWithBLOBs(@Param("record") Tbcomment record, @Param("example") TbcommentExample example);

    int updateByExample(@Param("record") Tbcomment record, @Param("example") TbcommentExample example);

    int updateByPrimaryKeySelective(Tbcomment record);

    int updateByPrimaryKeyWithBLOBs(Tbcomment record);

    int updateByPrimaryKey(Tbcomment record);
}