package com.whc.mix_api.mapper;

import com.whc.mix_api.model.CollectionVideo;
import com.whc.mix_api.model.vo.VideoVO;

import java.util.List;

public interface CollectionVideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CollectionVideo record);

    int insertSelective(CollectionVideo record);

    CollectionVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CollectionVideo record);

    int updateByPrimaryKey(CollectionVideo record);

    List<VideoVO> selectCollectionVideoVOList(Integer collectionId);
}