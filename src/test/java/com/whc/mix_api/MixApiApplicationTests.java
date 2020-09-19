package com.whc.mix_api;

import com.whc.mix_api.mapper.CastMemberMapper;
import com.whc.mix_api.mapper.CollectionMapper;
import com.whc.mix_api.mapper.VideoMapper;
import com.whc.mix_api.mapper.VideoTagMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@MapperScan("com.whc.mix_api.mapper")
class MixApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private VideoTagMapper videoTagMapper;
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private CastMemberMapper castMemberMapper;
    @Resource
    private CollectionMapper collectionMapper;

    @Test
    void getVideoTagVO() {
        System.out.println(videoTagMapper.getVideoTagVO(1));
    }

    @Test
    void getVideoVO() {
        System.out.println(videoMapper.selectVideoVO(1));
    }

    @Test
    void selectCastMemberVOList() {
        System.out.println(castMemberMapper.selectCastMemberVOList(1, 0));
    }

    @Test
    void selectLastUpdateTimeTenData() {
        System.out.println(collectionMapper.selectLastUpdateTimeTenData(0));
    }
}
