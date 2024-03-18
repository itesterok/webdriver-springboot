package com.housecallpro.pro.tests.pageobjects;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.housecallpro.pro.tests.utils.MatcherUtil;
import com.housecallpro.pro.tests.utils.WaitUntil;
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
public class JobDetailsPage implements LoadablePageOrComponent {

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private WaitUntil waitUntil;

    @FindBy(
        xpath =
            "//div[@role='button']/div[contains(@class, 'MuiExpansionPanelSummary-content-2057')]")
    private List<WebElement> activityFeedItems;

    public int getActivityFeedItemsCount() {
        return activityFeedItems.size();
    }

    public ZonedDateTime getLastJobCreationDateTime(ZoneId zone) {
        WebElement lastActivityItem = activityFeedItems.get(activityFeedItems.size() - 1);
        String text = lastActivityItem.getText();

        String date = MatcherUtil.match(text, "[0-9]+\\/[0-9]+\\/[0-9]+");
        String time = MatcherUtil.match(text, "[0-9]+:[0-9]+[apm]{2}");

        String dateTime = date.concat(" ").concat(time).toUpperCase();

        ZonedDateTime creationTime =
            ZonedDateTime.parse(dateTime, DateTimeFormatter.ofPattern("M/d/yy h:ma").withZone(zone));

        return creationTime;
    }

    @Override
    public void waitUntilLoaded() {
        waitUntil.visibilityOfElementLocated(By.xpath("//h3[contains(., 'Job costing breakdown')]"));
    }
}
