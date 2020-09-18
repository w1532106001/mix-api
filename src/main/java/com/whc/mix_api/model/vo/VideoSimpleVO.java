package com.whc.mix_api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author whc
 * @date 2020/9/18
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoSimpleVO {

    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "评分")
    private Double ratingNum;

    @ApiModelProperty(value = "封面")
    private String coverUrl;

    @ApiModelProperty(value = "观看数")
    private Integer watchNum;

    @ApiModelProperty(value = "收藏数")
    private Integer favoriteNum;
}
