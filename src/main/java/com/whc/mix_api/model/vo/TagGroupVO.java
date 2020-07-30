package com.whc.mix_api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author whc
 * @date 2020/7/30
 * @description
 */
@Data
public class TagGroupVO {
    @ApiModelProperty(value = "标签组名称")
    private String groupName;

    @ApiModelProperty(value = "标签列表")
    private List<TagVO> tagList;
}
