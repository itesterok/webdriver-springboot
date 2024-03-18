package com.housecallpro.pro.tests;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Application {
}
