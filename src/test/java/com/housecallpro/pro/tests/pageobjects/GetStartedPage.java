package com.housecallpro.pro.tests.pageobjects;

import java.util.List;

import com.housecallpro.pro.tests.pageobjects.newjob.NewJobPage;
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
public class GetStartedPage implements LoadablePageOrComponent {

    @Autowired
    private WaitUntil waitUntil;

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private PageLoader pageLoader;

    @FindBy(xpath = "//button[contains(., 'New')]")
    private WebElement newButton;

    @FindBy(xpath = "//ul[@role='menu']/li")
    private List<WebElement> allowedActionsList;

    @Step("Add entity {}")
    public NewJobPage addEntity(Entity entity) {
        newButton.click();
        waitUntil.sizeOfWebElementsInList(By.xpath("//ul[@role='menu']/li"), 5);

        allowedActionsList.stream()
            .filter(a -> a.getText().equalsIgnoreCase(entity.getDescription()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Element not found!"))
            .click();

        skipTutorialPageIfExists();
        return pageLoader.init(NewJobPage.class);
    }

    public void skipTutorialPageIfExists() {
        if (webDriver.getCurrentUrl().contains("first_time_use/introduction")) {
            waitUntil
                .visibilityOfElementLocated(By.xpath("//button[contains(., 'skip tutorial')]"))
                .click();
        }
    }

    @Override
    public void waitUntilLoaded() {
        waitUntil.visibilityOfElementLocated(By.cssSelector("button[data-testid]"));
    }

    public enum Entity {
        JOB("Job"),
        PROPOSAL("Proposal"),
        ESTIMATE("Estimate"),
        EVENT("Event"),
        CUSTOMER("Customer");

        String description;

        Entity(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
