package com.whc.mix_api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author whc
 * @date 2020/7/27
 * @description
 */
@Data
public class VideoVO {
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "评分")
    private Double ratingNum;

    @ApiModelProperty(value = "封面")
    private String coverUrl;

    @ApiModelProperty(value = "观看数")
    private Integer watchNum;

    @ApiModelProperty(value = "收藏数")
    private Integer favoriteNum;

    @ApiModelProperty(value = "标签")
    private List<TagVO> tagList;

    @ApiModelProperty(value = "导演")
    private List<CastMemberVO> directorList;

    @ApiModelProperty(value = "编剧")
    private List<CastMemberVO> writerList;

    @ApiModelProperty(value = "演员")
    private List<CastMemberVO> starList;

    @ApiModelProperty(value = "剧集")
    private List<VideoEpisodeVO> episodeList;
}
