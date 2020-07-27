package com.whc.mix_api.mapper;

import com.whc.mix_api.model.CollectionVideo;

public interface CollectionVideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CollectionVideo record);

    int insertSelective(CollectionVideo record);

    CollectionVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CollectionVideo record);

    int updateByPrimaryKey(CollectionVideo record);
}