package allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int PR = 91;

    //получается то же самое - есть 2 метода, это первый. В первом пишем весь код сразу здесь в коде, без аннотационных методов. Он берет source
    @Test
    public void testLambdaAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
        });
    }

    //2ой метод. Через стэпы.
    @Test
    public void testAnnotatedAttachments() {
        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        webSteps.takeScreenshot();
    }
}
