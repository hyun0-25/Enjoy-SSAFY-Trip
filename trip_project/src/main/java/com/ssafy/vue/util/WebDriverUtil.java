package com.ssafy.vue.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebDriverUtil {

	// WebDriver 경로
	@Value("${driver.chrome.driver_path}")
	private String WEB_DRIVER_PATH;

	public WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
		
		// webDriver 옵션 설정
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(true);
		chromeOptions.addArguments("--lang=ko");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--remote-debugging-port=50000");
		chromeOptions.setCapability("ignoreProtectedModeSettings", true);
		
		WebDriver driver = new ChromeDriver(chromeOptions);
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return driver;
	}

}
