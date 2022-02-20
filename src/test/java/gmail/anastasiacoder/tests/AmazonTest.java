package gmail.anastasiacoder.tests;

import gmail.anastasiacoder.annotations.JiraIssue;
import gmail.anastasiacoder.annotations.JiraIssues;
import gmail.anastasiacoder.annotations.Layer;
import gmail.anastasiacoder.annotations.Microservice;
import gmail.anastasiacoder.helpers.Attach;
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

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
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
    @Link(name = "Amazon", url = "https://www.amazon.com/")
    void titleTest() {
        mainpages.openMainPage();

        step("Тайтл страницы равен 'Amazon.com. Spend less. Smile more.'", () -> {
            String expectedTitle = "Amazon.com. Spend less. Smile more.";
            String actualTitle = title();
            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @MethodSource("gmail.anastasiacoder.tests.Footer#footerColumns")
    @DisplayName("Отображение значений")
    @Tags({@Tag("Blocker"), @Tag("High")})
    @Microservice("Footer")
    @Owner("Ambidre")
    @ParameterizedTest(name = "{displayName} {1} в футере сайта у колонки с названием {0}")
    @Feature("Футер")
    @Story("Футер основной страницы Amazon")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "Amazon", url = "https://www.amazon.com/")
    void displayValuesInTheFooterTest(String nameColumnFooter, List<String> footerColumns) {
        mainpages.openMainPage();

        step("Перейти в категорию " + nameColumnFooter, () -> {
            $$("div.navFooterLinkCol").find(text(nameColumnFooter)).$$("li")
                    .shouldHave(texts(footerColumns));
        });
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
    @Link(name = "Amazon", url = "https://www.amazon.com/")
    void displayOfAnonymousMenuItemTest(ProfileMenu profileMenu) {
        mainpages.openMainPage();

        step("Найти отображение пункта меню " + profileMenu + " в навигационной панели", () -> {
            $x("//a[@data-csa-c-type='link'] [@class='nav-a  ']").shouldHave(text(profileMenu.getProfileMenu()));
        });
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
    @Link(name = "Amazon", url = "https://www.amazon.com/")
    void searchResultsTest(String searchQuery) {
        mainpages.openMainPage();

        step("Найти системы VR Oculus Quest 2", () -> {
           $ ("#twotabsearchtextbox").setValue("Oculus quest 2").pressEnter();
        });
        step("Найти отображение товара " + searchQuery + " в результатах поиска", () -> {
            $$("div.s-main-slot").shouldHave(texts(searchQuery));
        });
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
    @Link(name = "Amazon", url = "https://www.amazon.com/")
    void filterCategoryTest(String searchQuery, String categoryName) {
        mainpages.openMainPage();

        step("Найти товар " + searchQuery, () -> {
            $("#twotabsearchtextbox").setValue(searchQuery).pressEnter();
        });
        step("Найти отображение категории " + categoryName + " в фильтре 'Категория'", () -> {
            $("#departments").shouldHave(text(categoryName));
        });
    }

    @MethodSource("gmail.anastasiacoder.tests.Category#productCategories")
    @DisplayName("Обзор по категориям на основной странице категории товара")
    @Tags({@Tag("Minor"), @Tag("Low")})
    @Microservice("Overview By Category")
    @Owner("Ambidre")
    @ParameterizedTest(name = "Отображение категорий {1} в обзоре по категориям на странице {0}")
    @Feature("Категории")
    @Story("Блок обзора по категориям")
    @Severity(SeverityLevel.MINOR)
    @Link(name = "Amazon", url = "https://www.amazon.com/")
    void displayOfTheOverviewByCategoryTest(String category, List<String> productCategories) {
        mainpages.openMainPage();

        step("Открыть каталог товаров", () -> {
            $("#nav-hamburger-menu").click();
        });
        step("Перейти в категорию " + category, () -> {
            $$(".hmenu-item").find(text(category)).click();
        });
        step("Найти отбражение категории " + productCategories + " в обзоре по категориям", () -> {
            $$x("//span[@class='topic-btn']").shouldHave(texts(productCategories));
        });
    }

    @Test
    @DisplayName("Отсутствие ошибки 'SEVERE' в консоли страницы")
    @Tags({@Tag("Critical"), @Tag("Highest")})
    @Microservice("Console")
    @Owner("Another QA")
    @Feature("Консоль")
    @Story("Журнал консоли")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Amazon", url = "https://www.amazon.com/")
    void consoleShouldNotHaveErrorsTest() {
        mainpages.openMainPage();

        step("Проверить отсутствие текста 'SEVERE' в консоли", () -> {
            String consoleLogs = Attach.browserConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}