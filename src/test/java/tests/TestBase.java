package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

//Это класс с твоими преднастройками , посленастройками (после каждого метода) и тд

public class TestBase {

    @BeforeAll
    static void setUpAll() {
        System.out.println("This  method goes before all");
        Configuration.pageLoadStrategy="eager";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.browser = "Chrome";
        Configuration.baseUrl = "https://demoqa.com";
        //Нижняя конфигурация нужна при подключении к удаленному компьютеру и проведении тестов ТАМ
        //Для подключения к удаленной машине нужно установить СЕЛЕНОИД, а чтобы установить его, то нужно установить ДОКЕР!
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        //Снизу конфигурация ЧИСТО для добавления видео (attachments) тестов в аллюр.
        /*DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;*/
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    /*@BeforeAll
    static void setUpForPlayground() {
        Configuration.baseUrl = "https://qaplayground.dev/";
    }*/
}
