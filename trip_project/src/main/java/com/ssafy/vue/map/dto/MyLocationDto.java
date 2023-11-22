package com.ssafy.vue.map.dto;

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
@ApiModel(value = "AttractionDto : 관광지정보", description = "관광지의 상세 정보를 나타낸다.")
public class MyLocationDto {
	@ApiModelProperty(value = "나의 여행 번호")
	private int mylocationId;
	@ApiModelProperty(value = "여행 코스 번호")
	private int courseId;
	@ApiModelProperty(value = "여행 코스 이름")
	private String courseName;
	@ApiModelProperty(value = "유저 아이디")
	private String userId;
	@ApiModelProperty(value = "관광지 번호")
	private int contentId;
	@ApiModelProperty(value = "여행 시작 일자")
	private String startDate;
	@ApiModelProperty(value = "여행 종료 일자")
	private String endDate;
	@ApiModelProperty(value = "여행 일자 순서")
	private String courseOrder;
	@ApiModelProperty(value = "여행 일자별 순서")
	private int order;
}
