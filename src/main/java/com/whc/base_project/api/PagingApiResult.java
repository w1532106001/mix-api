package com.whc.base_project.api;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页结果
 *
 * @author bianxinhuan
 */
@ApiModel("分页返回结果")
public class PagingApiResult<T> extends ApiResult<List<T>> {

    /**
     * 分页
     */
    @ApiModelProperty("分页信息")
    private Paging paging;

    public PagingApiResult(PageInfo pageInfo, List<T> list) {
        super(ApiResultCode.SUCCESS);

        paging = new Paging();
        paging.setPage(pageInfo.getPageNum());
        paging.setSize(pageInfo.getSize());
        paging.setTotal(pageInfo.getTotal());
        paging.setTotalPage(pageInfo.getPages());
        data = list;
    }

    public PagingApiResult(List<T> list) {
        super(ApiResultCode.SUCCESS);

        PageInfo pageInfo = new PageInfo<T>(list);
        paging = new Paging();
        paging.setPage(pageInfo.getPageNum());
        paging.setSize(pageInfo.getSize());
        paging.setTotal(pageInfo.getTotal());
        paging.setTotalPage(pageInfo.getPages());
        data = list;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
