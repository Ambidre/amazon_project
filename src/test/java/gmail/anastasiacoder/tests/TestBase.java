package gmail.anastasiacoder.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import gmail.anastasiacoder.config.WebDriverUtil;
import gmail.anastasiacoder.helpers.Attach;
import gmail.anastasiacoder.steps.MainPages;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static io.qameta.allure.Allure.step;

public class TestBase {

    MainPages mainpages = new MainPages();

    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        WebDriverUtil.configure();
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.attachVideo();
        step("Закрыть браузер", () -> {
            Selenide.closeWebDriver();
        });
    }
}