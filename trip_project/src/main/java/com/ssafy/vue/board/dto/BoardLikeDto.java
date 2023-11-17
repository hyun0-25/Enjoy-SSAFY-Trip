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
@ApiModel(value = "BoardLikeDto : 게시글 좋아요 정보", description = "게시글의 좋아요 상세 정보를 나타낸다.")

public class BoardLikeDto {
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "글번호")
	private int boardId;
}
