package com.whc.mix_api.service.impl;

import com.whc.mix_api.api.ApiResult;
import com.whc.mix_api.api.ApiResultBuilder;
import com.whc.mix_api.api.ApiResultCode;
import com.whc.mix_api.mapper.CastMemberMapper;
import com.whc.mix_api.mapper.PersonMapper;
import com.whc.mix_api.model.CastMember;
import com.whc.mix_api.model.Person;
import com.whc.mix_api.model.vo.PersonVO;
import com.whc.mix_api.model.vo.VideoSimpleVO;
import com.whc.mix_api.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author whc
 * @date 2020/9/18
 * @description
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonMapper personMapper;
    @Resource
    private CastMemberMapper castMemberMapper;

    @Override
    public ApiResult getPerson(Integer id) {
        ApiResult apiResult = checkPersonExist(id);
        if(apiResult.getCode()== ApiResultCode.SUCCESS.getCode()){
            Person person = (Person) apiResult.getData();
            PersonVO personVO = person.toPersonVO();
            List<CastMember> castMemberList = castMemberMapper.selectCastMemberList(person.getId());
            List<VideoSimpleVO> videoSimpleVOList = new ArrayList<>();
            castMemberList.stream().distinct()
            personVO.setVideoList(videoSimpleVOList);
        }

        return apiResult;
    }

    ApiResult checkPersonExist(Integer id){
        Person person = personMapper.selectByPrimaryKey(id);
        if(person!=null){
            return ApiResultBuilder.success(person);
        }
        return ApiResultBuilder.fail("数据不存在");
    }
}
