package com.housecallpro.pro.tests.pageobjects;

import com.housecallpro.pro.tests.utils.WaitUntil;
import com.housecallpro.pro.tests.utils.pageloader.PageLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginPage implements LoadablePageOrComponent {

    @Autowired
    private PageLoader pageLoader;

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private WaitUntil waitUntil;

    @Value("${credentials.email}")
    private String email;

    @Value("${credentials.password}")
    private String password;

    @Value("${baseUrl}")
    private String baseUrl;

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(xpath = "//button[contains(., 'Sign in')]")
    private WebElement signInButton;

    @Override
    public void load() {
        webDriver.navigate().to(baseUrl);
    }

    @Override
    public void waitUntilLoaded() {
        waitUntil.visibilityOfElementLocated(By.xpath("//button[contains(., 'Sign in')]"));
    }

    public GetStartedPage login() {
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
        signInButton.click();

        return pageLoader.init(GetStartedPage.class);
    }
}
