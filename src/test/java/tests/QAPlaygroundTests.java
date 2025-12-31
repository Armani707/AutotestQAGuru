package tests;

import org.junit.jupiter.api.Test;
import pages.QAPlaygroundPage;
import static com.codeborne.selenide.Selenide.*;
public class QAPlaygroundTests extends TestBase{

QAPlaygroundPage qaPlaygroundPagePage = new QAPlaygroundPage();

    @Test
    public void setCode () {
      qaPlaygroundPagePage.openCodePage().setNumbers("9").checkNumbersSuccess();
    }

    @Test
    public void openButton () {
        qaPlaygroundPagePage.openButtonPage().openBut();
        sleep(3000);
    }
}
