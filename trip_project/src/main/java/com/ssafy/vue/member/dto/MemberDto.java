package com.ssafy.vue.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MemberDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class MemberDto {

	@ApiModelProperty(value = "아이디")
	private String userId;
	@ApiModelProperty(value = "비밀번호")
	private String userPassword;
	@ApiModelProperty(value = "닉네임")
	private String nickname;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "가입일")
	private String joinDate;
	@ApiModelProperty(value = "유저/관리자 구분")
	private String role;
	@ApiModelProperty(value = "유저의 MZX 구분")
	private String userType;
	@ApiModelProperty(value = "refreshToken")
	private String token;
	
	
	
}
