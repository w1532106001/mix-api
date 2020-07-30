package com.whc.mix_api.mapper;

import com.whc.mix_api.model.Collection;
import com.whc.mix_api.model.vo.CollectionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    List<CollectionVO> selectLastUpdateTimeTenData(Integer type);

    List<CollectionVO> selectCollectionListByVideoId(@Param("videoId")Integer videoId,@Param("type") Integer type);
}