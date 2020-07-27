package com.whc.mix_api.mapper;

import com.whc.mix_api.model.Writer;

public interface WriterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Writer record);

    int insertSelective(Writer record);

    Writer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Writer record);

    int updateByPrimaryKey(Writer record);
}