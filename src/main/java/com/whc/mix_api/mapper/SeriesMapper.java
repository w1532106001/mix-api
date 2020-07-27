package com.whc.mix_api.mapper;

import com.whc.mix_api.model.Series;

public interface SeriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Series record);

    int insertSelective(Series record);

    Series selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Series record);

    int updateByPrimaryKey(Series record);
}