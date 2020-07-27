package com.whc.mix_api.service.impl;


import com.whc.mix_api.api.ApiResult;
import com.whc.mix_api.api.ApiResultBuilder;
import com.whc.mix_api.mapper.BannerMapper;
import com.whc.mix_api.mapper.CollectionMapper;
import com.whc.mix_api.model.Collection;
import com.whc.mix_api.model.vo.BannerVO;
import com.whc.mix_api.model.vo.CollectionVO;
import com.whc.mix_api.model.vo.VideoHomePageVO;
import com.whc.mix_api.service.IndexService;
import com.whc.mix_api.utils.ModelConversionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public ApiResult getVideoHomePageData() {
        BannerVO banner = bannerMapper.getBannerVO();
        VideoHomePageVO videoHomePageData = new VideoHomePageVO();
        videoHomePageData.setBanner(banner);
        List<Collection> collectionList = collectionMapper.getLastUpdateTimeTenData();
        List<CollectionVO> collectionVOList = new ArrayList<>();
        for (Collection collection : collectionList) {
            CollectionVO collectionVO = ModelConversionUtil.convertCollectionVO(collection);
            collection.
        }

        videoHomePageData.setCollectionList(collectionVOList);

        return ApiResultBuilder.success(videoHomePageData);
    }

    @Override
    public ApiResult getCategoryGroupList() {
//        List<CategoryGroupVO> categoryGroupVOList = new ArrayList<>();
//        CategoryGroupVO categoryGroupVO = null;
//        List<Category> categoryList = new ArrayList<>();
//        Category category;
//        for (int i = 0; i < 50; i++) {
//            if (i % 10 == 0) {
//                categoryList = new ArrayList<>();
//                categoryGroupVO = new CategoryGroupVO();
//                categoryGroupVO.setId(i / 10);
//                categoryGroupVO.setType(1);
//                categoryGroupVO.setValue("分类组" + i / 10);
//            }
//            category = new Category();
//            category.setId(i);
//            category.setName("分类" + (i + 1));
//            category.setCategoryGroupId(i / 10);
//            categoryList.add(category);
//            if ((i + 1) % 10 == 0) {
//                categoryGroupVO.setCategoryList(categoryList);
//                categoryGroupVOList.add(categoryGroupVO);
//            }
//        }
//        return ServerResponse.createBySuccess(categoryGroupVOList);
    }

    @Override
    public ApiResult getVideoCategoryPage(int pageSize, int currPage){
//        Page page = new Page();
//        page.setCurrPage(currPage);
//        List<VideoSimple> videoSimples = new ArrayList<>();
//        VideoSimple videoSimple;
//        if(currPage>=5){
//            page.setCanLoad(false);
//        }else{
//            for (int i = 1; i <= 20; i++) {
//                videoSimple = new VideoSimple();
//                videoSimple.setThumbnailUrl("https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2614500706.webp");
//                videoSimple.setVideoId(i);
//                videoSimple.setVideoName("视频"+i);
//                videoSimples.add(videoSimple);
//            }
//            page.setCanLoad(true);
//        }
//        page.setData(videoSimples);
//        page.setPageSize(videoSimples.size());
//        return ServerResponse.createBySuccess(page);
    }

    @Override
    public ApiResult getVideoSearchPage(int pageSize, int currPage) {
//        Page page = new Page();
//        page.setCurrPage(currPage);
//        List<VideoSimple> videoSimples = new ArrayList<>();
//        VideoSimple videoSimple;
//        if(currPage>=5){
//            page.setCanLoad(false);
//        }else{
//            for (int i = 1; i <= 20; i++) {
//                videoSimple = new VideoSimple();
//                videoSimple.setThumbnailUrl("https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2614500706.webp");
//                videoSimple.setVideoId(i);
//                videoSimple.setVideoName("视频"+i);
//                videoSimples.add(videoSimple);
//            }
//            page.setCanLoad(true);
//        }
//        page.setData(videoSimples);
//        page.setPageSize(videoSimples.size());
//        return ServerResponse.createBySuccess(page);
    }

    @Override
    public ApiResult getVideoDetailData(int videoId) {
//        VideoDetailVO videoDetailVO = new VideoDetailVO();
//        videoDetailVO.setName("十二生肖");
//        videoDetailVO.setSummary("\n" +
//                "                                　　当年英法联军火烧圆明园，致使大批珍贵文物流落海外，其中四尊十二生肖兽首最引人关注，不仅惹出国内外的广泛争论，更有收藏家开出天价竞拍这几尊珍品。当然，其间不乏奸邪的文物贩子，试图通过偷盗的手段获取宝贝。以此为契机，正在度假的国际大盗JC（成龙 饰）隆重登场。JC背后有一支 Simon（权相宇 饰）、David（廖凡 饰）、Bonnie（张蓝心 饰）等人共同组成的超专业团队，他们一同远赴巴黎，寻求国宝鉴定专家Coco（姚星彤 饰）的帮助。经过周密细致的准备，JC等人一步步逼近重兵把守的兽首，而围绕珍宝不可避免爆发连番惊险火爆的打斗与追逐。\n" +
//                "                                    <br>\n" +
//                "                                　　在这一过程中，JC似曾被利益和金钱泯灭的爱国之心渐渐苏醒……");
//        videoDetailVO.setRatingNum(6.7);
//        videoDetailVO.setWatchNum(277601L);
//        videoDetailVO.setEpisodeNum(1);
//        List<VideoEpisode> videoEpisodeList = new ArrayList<>();
//        VideoEpisode videoEpisode = new VideoEpisode();
//        videoEpisode.setVideoId("123");
//        videoEpisode.setName("1");
//        videoEpisode.setSort(0);
//        videoEpisode.setVideoUrl("");
//        videoEpisodeList.add(videoEpisode);
//        videoDetailVO.setVideoEpisodeList(videoEpisodeList);
//        List<Person> directorList = new ArrayList<>();
//        List<Person> writerList = new ArrayList<>();
//        List<Person> starList = new ArrayList<>();
//        Person person = new Person();
//        person.setName("成龙");
//        person.setId(1);
//        person.setAvatarUrl("https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1537891305.49.webp");
//        directorList.add(person);
//        writerList.add(person);
//        starList.add(person);
//        person = new Person();
//        person.setName("张蓝心");
//        person.setAvatarUrl("https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1537891242.2.webp");
//        person.setId(2);
//        starList.add(person);
//
//        List<Video> videoSeriesList = new ArrayList();
//        Video video = new Video();
//        video.setName("飞鹰计划");
//        video.setSummary("213");
//        video.setVideoEpisodeList(videoEpisodeList);
//        videoSeriesList.add(video);
//        videoDetailVO.setVideoSeriesList(videoSeriesList);
//        videoDetailVO.setDirectorList(directorList);
//        videoDetailVO.setWriterList(writerList);
//        videoDetailVO.setStarList(starList);
//        return ServerResponse.createBySuccess(videoDetailVO);
    }
}
