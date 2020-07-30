package com.whc.mix_api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author whc
 * @date 2020/7/30
 * @description
 */
@Data
public class VideoEpisodeVO {
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "播放地址")
    private String playUrl;

    @ApiModelProperty(value = "视频缩略图")
    private String thumbnailUrl;

    @ApiModelProperty(value = "观看数")
    private Integer watchNum;
}
