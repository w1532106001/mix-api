package com.whc.mix_api.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whc.mix_api.api.ApiResult;
import com.whc.mix_api.api.ApiResultBuilder;
import com.whc.mix_api.dto.VideoPageDTO;
import com.whc.mix_api.mapper.BannerMapper;
import com.whc.mix_api.mapper.CollectionMapper;
import com.whc.mix_api.mapper.TagGroupMapper;
import com.whc.mix_api.mapper.VideoMapper;
import com.whc.mix_api.model.Video;
import com.whc.mix_api.model.vo.*;
import com.whc.mix_api.service.IndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author whc
 * @date 2020/7/23
 * @description
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private BannerMapper bannerMapper;

    @Resource
    private CollectionMapper collectionMapper;

    @Resource
    private TagGroupMapper tagGroupMapper;

    @Resource
    private VideoMapper videoMapper;

    @Override
    public ApiResult getVideoHomePageData() {
        BannerVO banner = bannerMapper.getBannerVO();
        VideoHomePageVO videoHomePageData = new VideoHomePageVO();
        videoHomePageData.setBanner(banner);
        List<CollectionVO> collectionList = collectionMapper.selectLastUpdateTimeTenData(0);
        videoHomePageData.setCollectionList(collectionList);
        return ApiResultBuilder.success(videoHomePageData);
    }

    @Override
    public ApiResult getTagGroupList(Integer type) {
        List<TagGroupVO> tagGroupList = tagGroupMapper.selectGroupListByType(type);
        return ApiResultBuilder.success(tagGroupList);
    }

    @Override
    public ApiResult getVideoList(VideoPageDTO videoPageDTO){
        PageHelper.startPage(videoPageDTO.getCurrPage(), videoPageDTO.getPageSize());
        List<Video> videoList = videoMapper.selectVideoListByVideoPageDTO(videoPageDTO.getTagList(),videoPageDTO.getSearchWord());
        PageInfo page = new PageInfo(videoList);
        return ApiResultBuilder.success(page);
    }

    @Override
    public ApiResult getVideoDetailData(int videoId) {
        VideoDetailPageVO videoDetailPageVO = new VideoDetailPageVO();
        VideoVO videoVO = videoMapper.selectVideoVO(videoId);
        videoDetailPageVO.setVideo(videoVO);
        List<CollectionVO> collectionList = collectionMapper.selectCollectionListByVideoId(videoId,0);
        List<CollectionVO> seriesList = collectionMapper.selectCollectionListByVideoId(videoId,1);
        videoDetailPageVO.setCollectionList(collectionList);
        videoDetailPageVO.setSeriesList(seriesList);
        return ApiResultBuilder.success(videoDetailPageVO);

    }
}
