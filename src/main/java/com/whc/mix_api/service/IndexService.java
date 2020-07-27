package com.whc.mix_api.service;


import com.whc.mix_api.api.ApiResult;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * @author whc
 * @date 2020/7/23
 * @description
 */

public interface IndexService {
    ApiResult getVideoHomePageData();

    ApiResult getCategoryGroupList();

    ApiResult getVideoCategoryPage(int pageSize, int currPage);

    ApiResult getVideoSearchPage(int pageSize, int currPage);

    ApiResult getVideoDetailData(int videoId);
}
