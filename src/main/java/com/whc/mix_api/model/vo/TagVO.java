package com.whc.mix_api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author whc
 * @date 2020/7/30
 * @description
 */
@Data
public class TagVO {
    private Integer id;

    @ApiModelProperty(value = "组id")
    private Integer groupId;

    @ApiModelProperty(value = "名称")
    private String name;
}
