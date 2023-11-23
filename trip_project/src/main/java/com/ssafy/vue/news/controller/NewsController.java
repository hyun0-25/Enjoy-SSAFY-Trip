package com.ssafy.vue.news.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.member.controller.MemberController;
import com.ssafy.vue.member.model.service.MemberService;
import com.ssafy.vue.news.dto.NewsDto;
import com.ssafy.vue.news.model.service.NewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/news")
@Api("뉴스 크롤링 컨트롤러  API V1")
@Slf4j
public class NewsController {
	
	private NewsService newsService;
	public NewsController(NewsService newsService) {
		this.newsService=newsService;
	}
	
	@ApiOperation(value = "크롤링", notes = "크롤링 ")
	@GetMapping
	public ResponseEntity<?> news() throws IOException {
		List<NewsDto> newsList = newsService.getNewsDatas();
		log.info("newsList -{}",newsList);
		return new ResponseEntity<>(newsList,HttpStatus.OK);
	}
	

}
