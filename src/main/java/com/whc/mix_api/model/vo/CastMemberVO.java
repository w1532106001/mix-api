package com.whc.mix_api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author whc
 * @date 2020/7/30
 * @description
 */
@Data
public class CastMemberVO {
    @ApiModelProperty(value = "人id")
    private Integer personId;

    @ApiModelProperty(value = "人名")
    private String name;

    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "人物介绍")
    private String introduction;

    @ApiModelProperty(value = "0 导演 1编剧 2演员")
    private Integer type;
}
