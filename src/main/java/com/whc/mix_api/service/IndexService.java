package com.whc.mix_api.service;


import com.whc.mix_api.api.ApiResult;
import com.whc.mix_api.dto.VideoPageDTO;

/**
 * @author whc
 * @date 2020/7/23
 * @description
 */

public interface IndexService {
    ApiResult getVideoHomePageData();

    ApiResult getTagGroupList(Integer type);

    ApiResult getVideoList(VideoPageDTO videoPageDTO);

    ApiResult getVideoDetailData(int videoId);
}
