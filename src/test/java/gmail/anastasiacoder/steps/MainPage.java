package gmail.anastasiacoder.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import gmail.anastasiacoder.tests.ProfileMenu;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static gmail.anastasiacoder.tests.TestData.*;

public class MainPage {

    public static SelenideElement
            searchInput = $("#twotabsearchtextbox"),
            departmentTitle = $("#departments"),
            navSelector = $x("//a[@data-csa-c-type='link'] [@class='nav-a  ']"),
            catalogSelector = $("#nav-hamburger-menu");

    public static ElementsCollection
            resultOfSearch = $$("div.s-main-slot"),
            footerSelector = $$("div.navFooterLinkCol"),
            categorySelector  = $$(".hmenu-item"),
            categoryDisplaySelector = $$x("//span[@class='topic-btn']");

    @Step("Открыть главную страницу amazon ")
    public MainPage openMainPage() {
        open("https://www.amazon.com/");
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

    @Step("Проверка результат поиска товара")
    public MainPage checkResult(String searchQuery) {
        resultOfSearch.shouldHave(texts(searchQuery));
        return this;
    }

    @Step("Проверка отображения категории товара")
    public MainPage checkDepartment(String categoryName) {
        departmentTitle.shouldHave(text(categoryName));
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

    @Step("Открытие каталога товаров")
    public MainPage openCategory(String category) {
        categorySelector.find(text(category)).click();
        return this;
    }

    @Step("Проверка отображения категории")
    public MainPage checkCategory(List<String>  productCategories) {
        categoryDisplaySelector.shouldHave(texts(productCategories));
        return this;
    }
}