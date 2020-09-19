package com.whc.mix_api.controller;

import com.whc.mix_api.api.ApiResult;
import com.whc.mix_api.dto.VideoPageDTO;
import com.whc.mix_api.service.IndexService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: WHC
 * @Date: 2019/10/11 11:52
 * @description
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @PostMapping("")
    public String indexHtml() {
        return "index/index";
    }

    @Resource
    private IndexService indexService;

    @GetMapping("/videoHomePageData")
    public ApiResult getVideoHomePageData() {
        return indexService.getVideoHomePageData();
    }

    @GetMapping("/tagGroupList")
    public ApiResult getTagGroupList(@RequestParam("type") Integer type) {
        return indexService.getTagGroupList(type);
    }

    @GetMapping("/videoList")
    public ApiResult getVideoList(VideoPageDTO videoPageDTO) {
        return indexService.getVideoList(videoPageDTO);
    }

    @GetMapping("/videoDetailData")
    public ApiResult getVideoDetailData(@RequestParam("videoId") Integer videoId) {
        return indexService.getVideoDetailData(videoId);
    }
}
