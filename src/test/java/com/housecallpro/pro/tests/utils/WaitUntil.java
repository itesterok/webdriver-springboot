package com.housecallpro.pro.tests.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.awaitility.Awaitility.await;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WaitUntil {

    @Autowired
    private WebDriver webDriver;

    public WebElement visibilityOfElementLocated(By by) {
        WebElement webElement =
            new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
        Screenshotter.take(webDriver);
        return webElement;
    }

    public void sizeOfWebElementsInList(By by, int size) {
        await()
            .atMost(Duration.ofMinutes(1))
            .pollInterval(Duration.ofSeconds(2))
            .until(() -> webDriver.findElements(by).size() == size);
        Screenshotter.take(webDriver);
    }
}
