package com.whc.mix_api.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页信息
 *
 * @author bianxinhuan
 */
@ApiModel("分页信息")
public class Paging {

    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private Integer page;
    /**
     * 分页大小
     */
    @ApiModelProperty("分页大小")
    private Integer size;

    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private Integer totalPage;

    /**
     * 数据总条数
     */
    @ApiModelProperty("数据总量")
    private Long total;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
