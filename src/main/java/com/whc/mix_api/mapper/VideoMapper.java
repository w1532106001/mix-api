package com.whc.mix_api.mapper;

import com.whc.mix_api.model.Video;
import com.whc.mix_api.model.vo.VideoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    VideoVO selectVideoVO(Integer id);

    List<Video> selectVideoListByVideoPageDTO(@Param("tagList") List<String> tagList, @Param("searchWord") String searchWord);

    List<Video> selectVideoListByVideoIds(@Param("ids") List<Integer> ids);
}