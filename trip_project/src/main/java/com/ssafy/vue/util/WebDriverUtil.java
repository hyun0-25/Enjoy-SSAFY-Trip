package com.ssafy.vue.util;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class WebDriverUtil {

	// WebDriver 경로
	private static String WEB_DRIVER_PATH;

	public static WebDriver getChromeDriver() {
		if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
			System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
		}
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
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return driver;
	}

	@Value("${driver.chrome.driver_path}")
	public void initDriver(String path) {
		WEB_DRIVER_PATH = path;
	}

	public static void quit(WebDriver driver) {
		if (!ObjectUtils.isEmpty(driver)) {
			driver.quit();
		}
	}

	public static void close(WebDriver driver) {
		if (!ObjectUtils.isEmpty(driver)) {
			driver.close();
		}
	}

}
