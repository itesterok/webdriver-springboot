package com.housecallpro.pro.tests.utils.pageloader;

import com.housecallpro.pro.tests.pageobjects.LoadablePageOrComponent;
import lombok.AllArgsConstructor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class PageLoader {

    private final WebDriver webDriver;

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    public <T extends LoadablePageOrComponent> T init(Class<T> clazz) {
        return init(clazz, webDriver);
    }

    public <T extends LoadablePageOrComponent> T init(Class<T> clazz, SearchContext searchContext) {
        T page = applicationContextProvider.getApplicationContext().getBean(clazz);
        page.load();
        page.waitUntilLoaded();
        PageFactory.initElements(searchContext, page);
        return page;
    }
}
