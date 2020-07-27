package com.whc.mix_api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author whc
 * @date 2020/7/27
 * @description 视频合集对象
 */
@Data
public class CollectionVO {
    @ApiModelProperty(value = "合集名称")
    private String name;

    @ApiModelProperty(value = "视频列表")
    List<VideoVO> videoList;
}
