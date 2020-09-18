package com.whc.mix_api.service;

import com.whc.mix_api.api.ApiResult;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author whc
 * @date 2020/9/18
 * @description
 */

public interface PersonService {
    ApiResult getPerson(Integer id);
}
