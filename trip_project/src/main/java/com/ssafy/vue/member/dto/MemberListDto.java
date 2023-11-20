package com.ssafy.vue.member.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MemberDto : 게시글 목록 & 페이지 정보", description = "유저 목록과 현재 페이지와 전체 페이지 정보를 나타낸다.")
public class MemberListDto {
	@ApiModelProperty(value = "유저 목록")
	private List<MemberDto> members;
	@ApiModelProperty(value = "현재 페이지번호")
	private int currentPage;
	@ApiModelProperty(value = "전체 페이지 수")
	private int totalPageCount;
}
