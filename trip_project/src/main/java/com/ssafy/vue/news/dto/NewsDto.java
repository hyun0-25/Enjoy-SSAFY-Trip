package com.ssafy.vue.news.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@ApiModel(value = "NewsDto : 뉴스정보", description = "크롤링해온 뉴스정보")
public class NewsDto {
	private String imageNewsCompany;
	private String newsCompany;
	private String agoTime;
	private String url;
	private String title;
	
}
