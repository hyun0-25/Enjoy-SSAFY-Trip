package com.ssafy.vue.member.model.service;

import java.util.Map;

import com.ssafy.vue.member.dto.MemberDto;
import com.ssafy.vue.member.dto.MemberListDto;

public interface MemberService {

	Map<String, Object> loginMember(MemberDto member);
	MemberDto userInfo(String userId) throws Exception;
	
	int isCheck(String userId);
	void joinMember(MemberDto member);
	MemberListDto listMember();
	MemberDto getMember(String userId);
	void updateMember(MemberDto member);
	void deleteMember(String userId);
	
	
	
}
