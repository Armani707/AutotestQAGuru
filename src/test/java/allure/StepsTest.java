package allure;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int PR = 91;


    //Первый способ
    @Test
    public void testLambdaStep () {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий", () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText("eroshenkoam/allure-example")).click();
        });
        step("Открываем таб pull-requests", () -> {
            $("#pull-requests-tab").click();
        });
        step("Проверяем наличие pull-requests с номером " + PR, () -> {
            $(withText("#" + PR)).should(Condition.exist);
        });

    }


    //Второй способ
    @Test
    public void testAnnotatedStep() {
        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        webSteps.searchForRepository(REPOSITORY);
        webSteps.clickOnRepositoryLink(REPOSITORY);
        webSteps.openPRTab();
        webSteps.shouldSeePRWithNumber(PR);
    }
}
