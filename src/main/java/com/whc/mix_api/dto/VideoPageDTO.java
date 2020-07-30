package com.whc.mix_api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */
@Data
public class VideoPageDTO {
    private List<String> tagList;
    private String searchWord;
    private Integer pageSize;
    private Integer currPage;
}
