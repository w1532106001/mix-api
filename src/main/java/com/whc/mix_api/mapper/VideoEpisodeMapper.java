package com.whc.mix_api.mapper;

import com.whc.mix_api.model.VideoEpisode;
import com.whc.mix_api.model.vo.VideoEpisodeVO;

import java.util.List;

public interface VideoEpisodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoEpisode record);

    int insertSelective(VideoEpisode record);

    VideoEpisode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoEpisode record);

    int updateByPrimaryKey(VideoEpisode record);

    List<VideoEpisodeVO> selectVideoEpisodeVOList(Integer videoId);
}