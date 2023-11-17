package com.ssafy.vue.board.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "BoardCommentDto : 게시글의 댓글정보", description = "게시글의 댓글 정보를 나타낸다.")
public class BoardCommentDto {
	@ApiModelProperty(value = "댓글번호")
	private int commentId;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "게시판 아이디")
	private int boardId;
	@ApiModelProperty(value = "게시판 종류")
	private String boardType;
	@ApiModelProperty(value = "글내용")
	private String content;
	@ApiModelProperty(value = "작성일")	
	private String registerDate;
}
