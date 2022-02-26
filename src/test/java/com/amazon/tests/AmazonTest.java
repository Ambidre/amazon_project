package com.amazon.tests;

import com.amazon.helpers.Attach;
import com.amazon.annotations.JiraIssue;
import com.amazon.annotations.JiraIssues;
import com.amazon.annotations.Layer;
import com.amazon.annotations.Microservice;
import com.amazon.test_data.ProfileMenu;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Layer("web")
@Tags({@Tag("Web"), @Tag("UI")})
@JiraIssues({@JiraIssue("HOMEWORK-344")})
@DisplayName("Тестирование веб-приложения Amazon")
public class AmazonTest extends TestBase {

    @Test
    @DisplayName("У главной страницы есть тайтл")
    @Tags({@Tag("Blocker"), @Tag("High")})
    @Microservice("Menu Item")
    @Owner("Ambidre")
    @Feature("Меню")
    @Story("Панель пунктов меню")
    @Severity(SeverityLevel.BLOCKER)
    void titleTest() {
        mainPage.open()
                 .checkTitle();
    }

    @MethodSource("com.amazon.test_data.Footer#footerColumns")
    @DisplayName("Отображение значений")
    @Tags({@Tag("Blocker"), @Tag("High")})
    @Microservice("Footer")
    @Owner("Ambidre")
    @ParameterizedTest(name = "{displayName} {1} в футере сайта у колонки с названием {0}")
    @Feature("Футер")
    @Story("Футер основной страницы Amazon")
    @Severity(SeverityLevel.BLOCKER)
    void displayValuesInTheFooterTest(String nameColumnFooter, List<String> footerColumns) {
        mainPage.open()
                 .checkInformationInFooter(nameColumnFooter, footerColumns);
    }

    @EnumSource(ProfileMenu.class)
    @DisplayName("Пункты меню неавторизованного пользователя")
    @Tags({@Tag("Blocker"), @Tag("High")})
    @Microservice("Menu Item")
    @Owner("Ambidre")
    @ParameterizedTest(name = "Отображение пункта меню {0}")
    @Feature("Меню")
    @Story("Панель пунктов меню")
    @Severity(SeverityLevel.BLOCKER)
    void displayOfAnonymousMenuItemTest(ProfileMenu profileMenu) {
        mainPage.open()
                 .checkMenuItems(profileMenu);
    }

    @ValueSource(strings = {"Oculus Quest 2 — Advanced All-In-One Virtual Reality Headset — 256 GB",
            "Oculus Quest 2 — Advanced All-In-One Virtual Reality Headset — 128 GB"})
    @DisplayName("Результаты поиска")
    @Tags({@Tag("Blocker"), @Tag("High")})
    @Microservice("Search Results")
    @Owner("Ambidre")
    @ParameterizedTest(name = "Отображение товара {0} в результатах поиска")
    @Feature("Поиск")
    @Story("Страница результатов поиска")
    @Severity(SeverityLevel.BLOCKER)
    void searchResultsTest(String searchQuery) {
        mainPage.open()
                .searchProduct("Oculus quest 2");
        searchPage.checkResult(searchQuery);
    }

    @CsvSource(value = {
            "Gaming chair | Gaming Chairs",
            "bloody a90 gaming mouse | PC Accessories"
    }, delimiter = '|')
    @DisplayName("Фильтр 'Категория'")
    @Tags({@Tag("Minor"), @Tag("Low")})
    @Microservice("Filter Category")
    @Owner("Ambidre")
    @ParameterizedTest(name = "Отображение категории {1} в фильтре 'Категория'")
    @Feature("Фильтры")
    @Story("Блок фильтров")
    @Severity(SeverityLevel.MINOR)
    void filterCategoryTest(String searchQuery, String categoryName) {
        mainPage.open()
                 .searchProduct(searchQuery);
        searchPage.checkDepartment(categoryName);
    }

    @MethodSource("com.amazon.test_data.Category#productCategories")
    @DisplayName("Обзор по категориям на основной странице категории товара")
    @Tags({@Tag("Minor"), @Tag("Low")})
    @Microservice("Overview By Category")
    @Owner("Ambidre")
    @ParameterizedTest(name = "Отображение категорий {1} в обзоре по категориям на странице {0}")
    @Feature("Категории")
    @Story("Блок обзора по категориям")
    @Severity(SeverityLevel.MINOR)
    void displayOfTheOverviewByCategoryTest(String category, List<String> productCategories) {
        mainPage.open()
                 .openCatalog();
        catalogPage.openCategory(category)
                   .checkCategory(productCategories);
    }

    @Test
    @DisplayName("Отсутствие ошибки 'SEVERE' в консоли страницы")
    @Tags({@Tag("Critical"), @Tag("Highest")})
    @Microservice("Console")
    @Owner("Another QA")
    @Feature("Консоль")
    @Story("Журнал консоли")
    @Severity(SeverityLevel.CRITICAL)
    void consoleShouldNotHaveErrorsTest() {
        mainPage.open();

        step("Проверить отсутствие текста 'SEVERE' в консоли", () -> {
            String consoleLogs = Attach.browserConsoleLogs();
            String errorText = "SEVERE";
            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}