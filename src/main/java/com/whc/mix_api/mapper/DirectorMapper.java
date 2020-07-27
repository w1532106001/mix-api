package com.whc.mix_api.mapper;

import com.whc.mix_api.model.Director;

public interface DirectorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Director record);

    int insertSelective(Director record);

    Director selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Director record);

    int updateByPrimaryKey(Director record);
}