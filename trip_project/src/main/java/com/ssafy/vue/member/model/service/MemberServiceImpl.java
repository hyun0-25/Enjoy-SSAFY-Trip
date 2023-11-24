package com.ssafy.vue.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.vue.map.model.service.MapService;
import com.ssafy.vue.member.dto.MemberDto;
import com.ssafy.vue.member.dto.MemberListDto;
import com.ssafy.vue.member.model.mapper.MemberMapper;
import com.ssafy.vue.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;

	@Override
	public Map<String, Object> loginMember(MemberDto member) {
		MemberDto loginUser = memberMapper.loginMember(member);

		//해당 유저 정보 없음
		if(loginUser==null) return null;

		//토큰 생성
		String token = JWTUtil.generateToken(loginUser);

		//응답 데이터에 토큰 정보 구성
		Map<String, Object> tokenResponse = new HashMap<>();
		tokenResponse.put("token", token);

		return tokenResponse;
	}
	@Override
	public void joinMember(MemberDto member) {
		memberMapper.joinMember(member);
	}

	@Override
	public MemberListDto listMember() {
		List<MemberDto> list = memberMapper.listMember();
		MemberListDto memberListDto = new MemberListDto();
		memberListDto.setMembers(list);
		return memberListDto;
	}

	@Override
	public MemberDto getMember(String userId) {
		MemberDto selectUser = memberMapper.getMember(userId);
		return selectUser;
	}

	@Override
	public void updateMember(MemberDto member) {
		memberMapper.updateMember(member);		
	}

	@Override
	public void deleteMember(String userId) {
		memberMapper.deleteCourse(userId);
		memberMapper.deleteMember(userId);		
				
	}

	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return memberMapper.userInfo(userId);
	}

	@Override
	public int isCheck(String userId) {		
		return memberMapper.isCheck(userId);
	}

}
