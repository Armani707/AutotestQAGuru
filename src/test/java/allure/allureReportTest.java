package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class allureReportTest {


    @Test
    public void testIssueSearch () {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".search-input").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#pull-requests-tab").click();
        $(withText("#91")).should(Condition.exist);
    }

    //1:33:00 jenkins
    /*Jenkins. Чтобы подключить Jenkins, изначально нужно нажать Win + R, далее
    команда cmd, далее зайти в путь, где лежит jenkins.war через cd(Без jenkins.war в пути).
    Далее команда java -jar jenkins.war. Откроется jenkins, авторизироваться. Далее можно
    создать новую сборку (Создать Item), далее добавить репозиторий в jenkins в блоке Git,
    далее в поле Task добавить наименование таски для запуска.
    */

}
