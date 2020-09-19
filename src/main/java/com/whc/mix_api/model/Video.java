package com.whc.mix_api.model;

import com.whc.mix_api.model.vo.VideoSimpleVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Video implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "封面")
    private String coverUrl;

    @ApiModelProperty(value = "评分")
    private Double ratingNum;

    @ApiModelProperty(value = "观看数")
    private Integer watchNum;

    @ApiModelProperty(value = "收藏数")
    private Integer favoriteNum;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;


    public VideoSimpleVO toVideoSimpleVO() {
        VideoSimpleVO videoSimpleVO = new VideoSimpleVO();
        videoSimpleVO.setId(id);
        videoSimpleVO.setCoverUrl(coverUrl);
        videoSimpleVO.setFavoriteNum(favoriteNum);
        videoSimpleVO.setName(name);
        videoSimpleVO.setRatingNum(ratingNum);
        videoSimpleVO.setWatchNum(watchNum);
        return videoSimpleVO;
    }
}