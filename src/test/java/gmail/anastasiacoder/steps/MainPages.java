package gmail.anastasiacoder.steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class MainPages {

    @Step("Открыть главную страницу amazon ")
    public MainPages openMainPage() {
        open("https://www.amazon.com/");
        return this;
    }

}