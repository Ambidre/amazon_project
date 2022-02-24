package com.amazon.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.amazon.test_data.ProfileMenu;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.amazon.test_data.TestData.*;

public class MainPage {

    public static SelenideElement
            searchInput = $("#twotabsearchtextbox"),
            navSelector = $x("//a[@data-csa-c-type='link'] [@class='nav-a  ']"),
            catalogSelector = $("#nav-hamburger-menu");

    public static ElementsCollection
            footerSelector = $$("div.navFooterLinkCol");

    @Step("Открыть главную страницу amazon ")
    public MainPage open() {
        Selenide.open("https://www.amazon.com/");
        return this;
    }

    @Step("Проверка заголовка страницы")
    public void checkTitle() {
        String actualTitle = title();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Step("Поиск товара")
    public MainPage searchProduct(String productName) {
        searchInput.setValue(productName).pressEnter();
        return this;
    }

    @Step("Проверка отображения пунктов меню неавторизованного пользователя")
    public MainPage checkMenuItems(ProfileMenu profileMenu) {
        navSelector.shouldHave(text(profileMenu.getProfileMenu()));
        return this;
    }

    @Step("Проверка отображения информации в футере")
    public MainPage checkInformationInFooter(String nameColumnFooter, List<String> footerColumns) {
        footerSelector.find(text(nameColumnFooter)).$$("li")
                .shouldHave(texts(footerColumns));
        return this;
    }

    @Step("Открытие каталога товаров")
    public MainPage openCatalog() {
        catalogSelector.click();
        return this;
    }

}