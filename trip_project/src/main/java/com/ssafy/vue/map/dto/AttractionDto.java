package com.ssafy.vue.map.dto;

import java.util.List;

import com.ssafy.vue.board.dto.FileInfoDto;

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
public class AttractionDto {
	@ApiModelProperty(value = "관광지 번호")
	private int contentId;
	@ApiModelProperty(value = "관광지 종류 번호")
	private int contentTypeId;
	@ApiModelProperty(value = "관광지명")
	private String title;
	@ApiModelProperty(value = "관광지 주소")
	private String address;
	@ApiModelProperty(value = "시도 코드")
	private int sidoCode;
	@ApiModelProperty(value = "구군 코드")	
	private int gugunCode;
	@ApiModelProperty(value = "관광지 위도")	
	private double latitude;
	@ApiModelProperty(value = "관광지 경도")	
	private double longitude;
	@ApiModelProperty(value = "관광지 이미지1")	
	private String firstImage;
	@ApiModelProperty(value = "관광지 이미지2")	
	private String firstImage2;
	@ApiModelProperty(value = "관광지 정보")	
	private String overview;
}
