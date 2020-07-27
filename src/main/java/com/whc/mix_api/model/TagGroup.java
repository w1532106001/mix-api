package com.whc.mix_api.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class TagGroup implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "标签组名称")
    private String groupName;

    @ApiModelProperty(value = "0 视频 1小说 2漫画")
    private Integer category;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}