package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    //@Step("Открываем главную страницу")
    public void openMainPage () {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository (String repo) {
        $(".search-input").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink (String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем таб pull-requests")
    public void openPRTab () {
        $("#pull-requests-tab").click();
    }

    @Step("Проверяем наличие pull-requests с номером {PR}")
    public void shouldSeePRWithNumber (int PR) {
        $(withText("#" + PR)).should(Condition.exist);
    }

    //Метод аттачмент делает скриншот
    @Attachment(value = "Screenshot" /*название*/, type = "image/png", fileExtension = "png")
    public byte [] takeScreenshot () {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
