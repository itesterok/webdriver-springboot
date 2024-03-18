package com.housecallpro.pro.tests.pageobjects.newjob.blocks;

import java.util.List;

import com.housecallpro.pro.tests.pageobjects.LoadablePageOrComponent;
import com.housecallpro.pro.tests.pageobjects.newjob.NewJobPage;
import com.housecallpro.pro.tests.testdata.LineItem;
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
public class LineItems implements LoadablePageOrComponent {

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private PageLoader pageLoader;

    @FindBy(xpath = "//span[contains(., 'Services item')]//parent::div")
    private WebElement addServiceItemButton;

    @Step("Add service item")
    public LineItems addServiceItem(LineItem lineItem) {
        addServiceItemButton.click();
        List<WebElement> items =
            webDriver.findElements(By.xpath("//div[@data-testid='line-item-labor']"));
        WebElement lastItem = items.get(items.size() - 1);

        lastItem.findElement(By.id("item-name")).sendKeys(lineItem.getName());
        lastItem.findElement(By.id("description")).sendKeys(lineItem.getDescription());
        lastItem.findElement(By.id("qty")).sendKeys(lineItem.getQuantity());
        lastItem.findElement(By.id("unit-price")).sendKeys(lineItem.getUnitPrice());

        return this;
    }

    public NewJobPage added() {
        return pageLoader.init(NewJobPage.class);
    }

    @Override
    public void waitUntilLoaded() {
    }
}
