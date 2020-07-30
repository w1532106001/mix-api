package com.whc.mix_api.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author whc
 * @date 2020/7/30
 * @description
 */
@Data
public class VideoDetailPageVO {
    @ApiModelProperty("视频对象")
    private VideoVO video;
    @ApiModelProperty("合集")
    private List<CollectionVO> collectionList;
    @ApiModelProperty("系列")
    private List<CollectionVO> seriesList;
}
