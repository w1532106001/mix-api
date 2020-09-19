package com.whc.mix_api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author whc
 * @date 2020/9/18
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVO {
    private Integer id;

    @ApiModelProperty(value = "人名")
    private String name;

    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "人物介绍")
    private String introduction;

    @ApiModelProperty(value = "视频列表")
    private List<VideoSimpleVO> videoList;


}
