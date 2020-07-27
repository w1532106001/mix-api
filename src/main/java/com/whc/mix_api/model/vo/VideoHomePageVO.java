package com.whc.mix_api.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author whc
 * @date 2020/7/27
 * @description
 */
@Data
public class VideoHomePageVO {
    private BannerVO banner;
    private List<CollectionVO> collectionList;
}
