package com.amazon.tests;

import com.amazon.helpers.Attach;
import com.amazon.pageobjects.CatalogPage;
import com.amazon.pageobjects.MainPage;
import com.amazon.pageobjects.SearchPage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.amazon.config.LocalDriverUtil;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.qameta.allure.Allure.step;

public class TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    CatalogPage catalogPage = new CatalogPage();


    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        LocalDriverUtil.configure();
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}