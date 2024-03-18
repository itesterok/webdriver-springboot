package com.housecallpro.pro.tests.config.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class WebDriverFactory {
    private final ThreadLocal<WebDriver> webDriverThreadLocal = ThreadLocal.withInitial(() -> null);

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriver getWebDriver() {
        if (webDriverThreadLocal.get() == null) {
            WebDriver localWebDriver = provisionWebDriver();
            webDriverThreadLocal.set(localWebDriver);
        }
        return webDriverThreadLocal.get();
    }

    private WebDriver provisionWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        //        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(options);

        Runtime.getRuntime().addShutdownHook(new Thread(webDriver::quit));

        return webDriver;
    }
}
