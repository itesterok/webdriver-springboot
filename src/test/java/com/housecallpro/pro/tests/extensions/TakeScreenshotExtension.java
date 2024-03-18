package com.housecallpro.pro.tests.extensions;

import com.housecallpro.pro.tests.utils.Screenshotter;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public final class TakeScreenshotExtension implements BeforeEachCallback, AfterEachCallback {

    private ThreadLocal<WebDriver> webDriver;

    @Override
    public void beforeEach(ExtensionContext context) {
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(context);
        WebDriver driver = applicationContext.getBean(WebDriver.class);
        this.webDriver = ThreadLocal.withInitial(() -> driver);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        Screenshotter.take(webDriver.get());
    }

    public WebDriver getWebDriver() {
        return webDriver.get();
    }
}
