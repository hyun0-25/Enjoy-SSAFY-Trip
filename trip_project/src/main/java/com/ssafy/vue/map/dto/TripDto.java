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
public class TripDto {
	@ApiModelProperty(value = "여행 코스 번호")
	private int courseId;
	@ApiModelProperty(value = "여행 코스 이름")
	private String courseName;
	@ApiModelProperty(value = "여행 시작 일자")
	private String startDate;
	@ApiModelProperty(value = "여행 종료 일자")
	private String endDate;
}
