package com.ssafy.vue.news.model.service;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ssafy.vue.news.dto.NewsDto;
import com.ssafy.vue.util.WebDriverUtil;

@Service
public class NewsServiceImpl implements NewsService {

	private WebDriverUtil webDriverUtil;

	public NewsServiceImpl(WebDriverUtil webDriverUtil) {
		this.webDriverUtil = webDriverUtil;
	}
	
	//구글
	private static String News_URL = "https://www.google.com/search?q=%EC%97%AC%ED%96%89&sca_esv=584771578&tbm=nws&sxsrf=AM9HkKnp27Hebp2wcY3B_b-PL7xXWfvm9A:1700714392098&source=lnms&sa=X&ved=2ahUKEwj49tHpptmCAxXRePUHHTcdBBUQ_AUoAXoECAEQAw&biw=1920&bih=963&dpr=1";
	
//	private static String News_URL = "https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=%EC%97%AC%ED%96%89";

	@Override
	public List<NewsDto> getNewsDatas() throws IOException {

		WebDriver driver = webDriverUtil.getChromeDriver();
		
		driver.get(News_URL);
		System.out.println(driver.getPageSource());
		
		
		return null;
		
//		List<WebElement> webElementList = new ArrayList<>();
//		List<NewsDto> newsList = new ArrayList<>();
//		String crawl="";
//		if (!ObjectUtils.isEmpty(driver)) {
//			driver.get(News_URL);
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//			webElementList = driver.findElements(By.cssSelector("div .info_group"));
//			WebElement elementTitle = driver.findElement(By.cssSelector(".iRPxbe"));
//            WebElement elementPlace = driver.findElement(By.cssSelector(".news_contents"));
//            WebElement elementDate = driver.findElement(By.cssSelector(".dsc_wrap"));
//            System.out.println(elementTitle);
//		}
//		List<String> a = new ArrayList<String>();
//		for (WebElement list : webElementList) {
//			a.add(list.getAttribute("href"));
//		}
//		System.out.println(a);
//		NewsDto n = NewsDto.builder()
//				.agoTime("a").build();
//		newsList.add(n);
//		driver.close();
//		return newsList;
	}

	
}
