package com.whc.mix_api.controller;

import com.whc.mix_api.api.ApiResult;
import com.whc.mix_api.api.ApiResultBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author whc
 * @date 2020/8/27
 * @description
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    ApiResult getVideoById(Integer id) {
        return ApiResultBuilder.success();
    }


}
