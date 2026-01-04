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

    //���������� �� �� ����� - ���� 2 ������, ��� ������. � ������ ����� ���� ��� ����� ����� � ����, ��� ������������� �������. �� ����� source
    @Test
    public void testLambdaAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("��������� ������� ��������", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
        });
    }

    //2�� �����. ����� �����.
    @Test
    public void testAnnotatedAttachments() {
        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        webSteps.takeScreenshot();
    }
}
