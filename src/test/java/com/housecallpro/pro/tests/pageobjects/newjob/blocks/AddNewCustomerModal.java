package com.housecallpro.pro.tests.pageobjects.newjob.blocks;

import com.housecallpro.pro.tests.pageobjects.LoadablePageOrComponent;
import com.housecallpro.pro.tests.pageobjects.newjob.NewJobPage;
import com.housecallpro.pro.tests.utils.pageloader.PageLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddNewCustomerModal implements LoadablePageOrComponent {

    @Autowired
    private PageLoader pageLoader;

    @FindBy(id = "customer-dialog-first-name")
    private WebElement firstNameInputField;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement lastNameInputField;

    @FindBy(xpath = "//input[@name='display_name']")
    private WebElement displayNameInputField;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInputField;

    @FindBy(xpath = "//input[@name='mobile_number']")
    private WebElement mobileNumberInputField;

    @FindBy(xpath = "//input[@name='company']")
    private WebElement companyInputField;

    @FindBy(xpath = "//button[contains(., 'create')]")
    private WebElement createCustomerButton;

    @Step("Set first name")
    public AddNewCustomerModal setFirstName(String value) {
        firstNameInputField.sendKeys(value);
        return this;
    }

    @Step("Set lat name")
    public AddNewCustomerModal setLastName(String value) {
        lastNameInputField.sendKeys(value);
        return this;
    }

    @Step("Set mobile number")
    public AddNewCustomerModal setMobileNumber(String value) {
        mobileNumberInputField.sendKeys(value);
        return this;
    }

    @Step("Set company")
    public AddNewCustomerModal setCompany(String value) {
        companyInputField.sendKeys(value);
        return this;
    }

    @Step("Create customer")
    public NewJobPage createCustomer() {
        createCustomerButton.click();
        return pageLoader.init(NewJobPage.class);
    }

    @Override
    public void waitUntilLoaded() {
    }
}
