package com.whc.mix_api.mapper;

import com.whc.mix_api.model.TagGroup;
import com.whc.mix_api.model.vo.TagGroupVO;

import java.util.List;

public interface TagGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TagGroup record);

    int insertSelective(TagGroup record);

    TagGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TagGroup record);

    int updateByPrimaryKey(TagGroup record);

    List<TagGroupVO> selectGroupListByType(Integer type);
}