package com.housecallpro.pro.tests.pageobjects.newjob;

import java.util.List;

import com.housecallpro.pro.tests.pageobjects.JobDetailsPage;
import com.housecallpro.pro.tests.pageobjects.LoadablePageOrComponent;
import com.housecallpro.pro.tests.pageobjects.newjob.blocks.AddNewCustomerModal;
import com.housecallpro.pro.tests.pageobjects.newjob.blocks.LineItems;
import com.housecallpro.pro.tests.utils.WaitUntil;
import com.housecallpro.pro.tests.utils.pageloader.PageLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NewJobPage implements LoadablePageOrComponent {

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private WaitUntil waitUntil;

    @Autowired
    private PageLoader pageLoader;

    @FindBy(xpath = "//span[contains(., 'New customer')]")
    private WebElement newCustomerButton;

    @FindBy(xpath = "//h4[contains(text(),'1')]")
    private WebElement customerBlock;

    @FindBy(xpath = "//h4[contains(text(),'3')]")
    private WebElement lineItemsBlock;

    @FindBy(xpath = "//p[contains(text(),'Private notes')]//parent::div")
    private WebElement privateNotesButton;

    @FindBy(xpath = "//textarea[@data-testid='private-notes-textfield']")
    private WebElement privateNotesInputField;

    @FindBy(xpath = "//span[contains(text(), 'Save job')]//parent::button")
    private WebElement saveJobButton;

    @FindBy(xpath = "//ul[@role='menu']/li")
    private List<WebElement> allowedActionsList;

    @Step("Add new customer")
    public AddNewCustomerModal addNewCustomer() {
        customerBlock.click();
        newCustomerButton.click();

        WebElement modalWindow = webDriver.findElement(By.xpath("//div[@role = 'dialog']"));

        return pageLoader.init(AddNewCustomerModal.class, modalWindow);
    }

    @Step("Add private notes")
    public NewJobPage addPrivateNotes(String value) {
        privateNotesButton.click();
        privateNotesInputField.sendKeys(value);
        return this;
    }

    public LineItems addLineItems() {
        lineItemsBlock.click();

        WebElement context =
            webDriver.findElement(By.xpath("//h2[contains(., 'Line items')]//parent::div"));
        return pageLoader.init(LineItems.class, context);
    }

    @Step("Save job")
    public JobDetailsPage saveJob() {
        saveJobButton.click();

        return pageLoader.init(JobDetailsPage.class);
    }

    @Override
    public void waitUntilLoaded() {
        waitUntil.visibilityOfElementLocated(By.xpath("//span[@class='MuiButton-label']"));
    }
}
