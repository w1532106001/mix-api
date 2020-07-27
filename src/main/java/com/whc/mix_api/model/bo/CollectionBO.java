package com.whc.mix_api.model.bo;

import com.whc.mix_api.model.Collection;
import com.whc.mix_api.model.Video;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author whc
 * @date 2020/7/27 22:00
 */
@Data
public class CollectionBO {
    private Integer id;

    @ApiModelProperty(value = "合集名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    private List<Video> videoList;
}
