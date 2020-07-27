package com.whc.mix_api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author whc
 * @date 2020/7/27
 * @description
 */
@Data
public class BannerVO {
    @ApiModelProperty(value = "视频id")
    private Integer videoId;

    @ApiModelProperty(value = "banner 名称")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "图片地址")
    private String bannerUrl;

    @ApiModelProperty(value = "评分")
    private double ratingNum;
}
