package com.housecallpro.pro.tests.scenarios;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.housecallpro.pro.tests.extensions.TakeScreenshotExtension;
import com.housecallpro.pro.tests.pageobjects.JobDetailsPage;
import com.housecallpro.pro.tests.pageobjects.LoginPage;
import com.housecallpro.pro.tests.testdata.LineItem;
import com.housecallpro.pro.tests.utils.pageloader.PageLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.housecallpro.pro.tests.pageobjects.GetStartedPage.Entity.JOB;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Execution(ExecutionMode.CONCURRENT)
public class NewJobTest {

    @RegisterExtension
    private final TakeScreenshotExtension screenshotExtension =
        new TakeScreenshotExtension();

    @Autowired
    private PageLoader pageLoader;

    @Test
    public void login() {
        // given
        ZoneId zone = ZoneId.of("-04:00");
        ZonedDateTime jobCreationTime = ZonedDateTime.now(zone);

        // when
        JobDetailsPage jobDetailsPage =
            pageLoader
                .init(LoginPage.class)
                .login()
                .addEntity(JOB)
                .addNewCustomer()
                .setFirstName("Bebe")
                .setLastName("Dede")
                .setCompany("Company")
                .setMobileNumber("2345678908")
                .createCustomer()
                .addLineItems()
                .addServiceItem(
                    LineItem.builder()
                        .name("name")
                        .description("description")
                        .quantity("1")
                        .unitPrice("120")
                        .build())
                .added()
                .addPrivateNotes("Hi there!")
                .saveJob();

        // then
        assertThat(jobDetailsPage.getActivityFeedItemsCount()).describedAs("Bla bla bla").isEqualTo(1);
        assertThat(jobDetailsPage.getLastJobCreationDateTime(zone))
            .isBetween(jobCreationTime.minusMinutes(1), jobCreationTime.plusMinutes(1));
    }
}
