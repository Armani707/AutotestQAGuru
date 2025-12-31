package pages;

import com.codeborne.selenide.SelenideElement;


import java.io.File;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

/*Это класс куда ты выносишь весь кодовый мусор связанный с какой-то страницей с которой вы работаете, чтобы непосредственно в тесте
тест выглядел максимально читабельно и аккуратно*/

public class RegistrationPage {


    SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            genderPick = $("#genterWrapper"),
            userNumber = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            selectMonthBirth = $(".react-datepicker__month-select"),
            selectYearBirth = $(".react-datepicker__year-select"),
            selectBirthDay = $(".react-datepicker__month-container"),
            emailInput = $("#userEmail"),
            subjectInput = $(" #subjectsInput"),
            hobbyInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
            responses = $(".table-responsive");



    public RegistrationPage setBirthday(String birthday, String month, String year) {
        $(dateOfBirthInput).click();
        $(selectYearBirth).click();
        $(byText(year)).click();
        $(selectMonthBirth).click();
        $(byText(month)).click();
        $(selectBirthDay).shouldBe(visible).
        $(byText(birthday)).click();
        return this;
    }

    public RegistrationPage openPagePracticeForm() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        $(firstNameInput).setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
    $(lastNameInput).setValue(value);
        return this;
    }

    public RegistrationPage setEmail (String email) {
        $(emailInput).setValue(email);
        return this;
    }

    public RegistrationPage setGender (String gender) {
    $(genderPick).$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setUserNumber (String number) {
    $(userNumber).setValue(number);
        return this;
    }

    public RegistrationPage setSubject (String subject1, String subject2) {
    $(subjectInput).setValue(subject1).pressEnter().setValue(subject2).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby1, String hobby2) {
        $(hobbyInput).$(byText(hobby1)).click();
        $(hobbyInput).$(byText(hobby2)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String pictureURL) {
        $(pictureInput).uploadFile(new File(pictureURL));
        return this;
    }

    public RegistrationPage setAddress (String address) {
    $(addressInput).setValue(address);
        return this;
    }

    public RegistrationPage selectState (String state) {
    $(stateInput).click();
    $(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCity (String city) {
    $(cityInput).click();
    $(byText(city)).click();
        return this;
    }


    public RegistrationPage submitPracticeFormButton() {
        $(submitButton).click();
        return this;
    }


    public void checkPracticeForm(String nameSurname, String eMail, String gender, String userNumber, String birthday,
                                  String subject, String hobby, String pictureURL, String address, String state, String city) {

        final String text = responses.text();
        assertAll(() -> assertTrue(text.contains(nameSurname)),
        () -> assertTrue(text.contains(eMail)),
        () -> assertTrue(text.contains(gender)),
        () -> assertTrue(text.contains(birthday)),
        () -> assertTrue(text.contains(subject)),
        () -> assertTrue(text.contains(hobby)),
        () -> assertTrue(text.contains(pictureURL)),
        () -> assertTrue(text.contains(address)),
        () -> assertTrue(text.contains(state)),
        () -> assertTrue(text.contains(city)),
        () -> assertTrue(text.contains(userNumber)));

        //МОЖНО ИСПОЛЬЗОВАТЬ EQUALS если есть точное понимание, какое значение мы ждем




        /* $(responses).shouldHave(text(nameSurname),
                text(eMail),
                text(gender),
                text(userNumber),
                text(birthday),
                text(subject),
                text(hobby),
                text(pictureURL),
                text(address),
                text(state),
                text(city));*/

    }
}
