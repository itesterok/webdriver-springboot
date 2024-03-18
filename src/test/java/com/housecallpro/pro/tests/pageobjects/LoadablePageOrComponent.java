package com.housecallpro.pro.tests.pageobjects;

public interface LoadablePageOrComponent {

    default void load() {
    }

    void waitUntilLoaded();
}
