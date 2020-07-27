package com.whc.mix_api.mapper;

import com.whc.mix_api.model.SeriesVideo;

public interface SeriesVideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SeriesVideo record);

    int insertSelective(SeriesVideo record);

    SeriesVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SeriesVideo record);

    int updateByPrimaryKey(SeriesVideo record);
}