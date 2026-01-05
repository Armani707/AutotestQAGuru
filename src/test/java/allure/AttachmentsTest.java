package allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest extends TestBase {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int PR = 91;

    //есть 2 способа аттачмента. Первый это через скобки (лямбда)
    @Test
    public void testLambdaAttachments() {
        step("????????? ??????? ????????", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
        });
    }

    //2 способ. Аттачмент через аннотации, вызов таких методов с аннотациями аттачмент
    @Test
    public void testAnnotatedAttachments() {

        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        sleep(1000);
        webSteps.takeScreenshot();
        //webSteps.addVideo();

    }
}
