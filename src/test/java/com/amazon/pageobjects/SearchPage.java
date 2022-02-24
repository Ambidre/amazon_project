package com.amazon.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    public static SelenideElement
            departmentTitle = $("#departments");

    public static ElementsCollection
            resultOfSearch = $$("div.s-main-slot");

    @Step("Проверка результат поиска товара")
    public SearchPage checkResult(String searchQuery) {
        resultOfSearch.shouldHave(texts(searchQuery));
        return this;
    }

    @Step("Проверка отображения категории товара")
    public SearchPage checkDepartment(String categoryName) {
        departmentTitle.shouldHave(text(categoryName));
        return this;
    }
}
