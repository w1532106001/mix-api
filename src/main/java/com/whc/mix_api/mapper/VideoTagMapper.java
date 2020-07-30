package com.whc.mix_api.mapper;

import com.whc.mix_api.model.VideoTag;
import com.whc.mix_api.model.vo.TagVO;

import java.util.List;

public interface VideoTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoTag record);

    int insertSelective(VideoTag record);

    VideoTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoTag record);

    int updateByPrimaryKey(VideoTag record);

    List<TagVO> getVideoTagVO(Integer id);

}