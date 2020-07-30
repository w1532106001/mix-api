package com.whc.mix_api.mapper;

import com.whc.mix_api.model.CastMember;
import com.whc.mix_api.model.vo.CastMemberVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CastMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CastMember record);

    int insertSelective(CastMember record);

    CastMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CastMember record);

    int updateByPrimaryKey(CastMember record);

    /**
     * 人员列表
     * @param videoId 视频id
     * @return
     */
    List<CastMemberVO> selectCastMemberVOList(@Param("videoId") int videoId,@Param("type") int type);
}