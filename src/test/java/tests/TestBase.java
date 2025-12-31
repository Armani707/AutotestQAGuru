package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

//Это класс с твоими преднастройками , посленастройками (после каждого метода) и тд

public class TestBase {

    @BeforeAll
    static void setUpAll() {
        System.out.println("This  method goes before all");
        Configuration.pageLoadStrategy="eager";
        Configuration.browserSize = "1920x1080";
        //Configuration.baseUrl = "https://demoqa.com";
    }

    /*@BeforeAll
    static void setUpForPlayground() {
        Configuration.baseUrl = "https://qaplayground.dev/";
    }*/
}
