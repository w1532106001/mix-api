package com.whc.mix_api.mapper;

import com.whc.mix_api.model.Tag;
import com.whc.mix_api.model.vo.TagVO;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<TagVO> selectTagVOListByTagGroupId(Integer tagGroupId);
}