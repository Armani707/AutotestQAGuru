package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("open Main Page")
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

    //Метод аттачмент делает видео (не работает без СЕЛЕНОИДА И ДОКЕРА)
    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public String addVideo() {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl()
                + "' type='video/mp4'></video></body></html>";
    }

    //Метод связан с методом addVideo
    public static URL getVideoUrl() {
        String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId() + ".mp4";
        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
