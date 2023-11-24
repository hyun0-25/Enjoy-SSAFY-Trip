package com.ssafy.vue.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.member.dto.MemberDto;
@Mapper
public interface MemberMapper {

	MemberDto loginMember(MemberDto member);
	MemberDto userInfo(String userId) throws SQLException;
	
	int isCheck(String userId);	
	void joinMember(MemberDto member);
	List<MemberDto> listMember();
	MemberDto getMember(String userId);
	void updateMember(MemberDto member);
	void deleteMember(String userId);
	void deleteCourse(String userId);
	
	
}
