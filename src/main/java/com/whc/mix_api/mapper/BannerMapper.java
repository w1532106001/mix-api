package com.whc.mix_api.mapper;

import com.whc.mix_api.model.Banner;
import com.whc.mix_api.model.vo.BannerVO;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

    BannerVO getBannerVO();
}