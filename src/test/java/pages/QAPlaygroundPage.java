package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class QAPlaygroundPage {

    ElementsCollection codeClass = $$(".code");
    ElementsCollection buttonClass = $$(".nav-item");
    SelenideElement successCodeLocator = $(".container");

    public QAPlaygroundPage openCodePage () {
        open("apps/verify-account/");
        return this;
    }

    public QAPlaygroundPage setNumbers (String number) {
        codeClass.asFixedIterable().forEach(element -> element.setValue(number));
        return this;
    }

    public QAPlaygroundPage checkNumbersSuccess () {
        final String successText = successCodeLocator.text();
        assertAll(() -> assertEquals("Success", successText));
        return this;
    }

    public QAPlaygroundPage openBut () {
        buttonClass.get(3).click();
        return this;
    }

    public QAPlaygroundPage openButtonPage () {
        open("apps/multi-level-dropdown/");
        return this;
    }
}
