package com.ssafy.vue.news.model.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.vue.news.dto.NewsDto;

public interface NewsService {
	public List<NewsDto> getNewsDatas() throws IOException;

}
