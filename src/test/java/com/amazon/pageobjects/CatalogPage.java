package com.amazon.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {

    public static ElementsCollection
            categorySelector  = $$(".hmenu-item"),
            categoryDisplaySelector = $$x("//span[@class='topic-btn']");

    @Step("Переход по категории товара")
    public CatalogPage openCategory(String category) {
        categorySelector.find(text(category)).click();
        return this;
    }

    @Step("Проверка отображения категории")
    public CatalogPage checkCategory(List<String>  productCategories) {
        categoryDisplaySelector.shouldHave(texts(productCategories));
        return this;
    }
}
