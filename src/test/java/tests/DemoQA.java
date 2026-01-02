package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.RegistrationPage;
import pages.data.Language;
import util.UtilsForRandom;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

//класс с непосредственно тестами

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) Аннотация над классом для добавления @Order над тестами
public class DemoQA extends TestBase {


    RegistrationPage registrationPage = new RegistrationPage();
    UtilsForRandom utils = new UtilsForRandom();
    Faker faker = new Faker();
    String randomFirstName = faker.name().firstName();
    String randomLastName = faker.name().lastName();
    public String randomEmail = faker.internet().emailAddress();
    String randomGender = utils.randomGender();
    String randomNumber = utils.randomUserNumber();
    String randomBirthDay = utils.randomDay();
    String randomBirthMonth = utils.randomMonth();
    String randomBirthYear = utils.randomYear();

    String randomSubject1 = utils.randomSubjects();
    String randomSubject2 = utils.randomSubjects();
    String randomHobby1 = utils.randomHobbies();
    String randomHobby2 = utils.randomHobbies();
    String randomPictureURL = utils.randomPictureURLs();
    String randomAddress = faker.address().fullAddress();
    String randomState = utils.randomState();
    String randomCity;

    public void connectStatesAndCities () {
        System.out.println("Now we connect States And Cities");
        switch (randomState) {
            case "NCR" -> randomCity = utils.randomCitiesForNCR();
            case "Uttar Pradesh" -> randomCity = utils.randomCitiesForUttarPradesh();
            case "Haryana" -> randomCity = utils.randomCitiesForHaryana();
            case "Rajasthan" -> randomCity = utils.randomCitiesForRajasthan();
        }
    }

   //@BeforeEach - это предусловие тест-кейса, туда выносим предусловия чтобы в самих тест-кейсах не было лишних шагов



  @ParameterizedTest(name = "Используется имя {0}") //показываем что тест параметризован, есть название теста, а в фигурных скобках индекс объекта из аргументов которые мы выбрали для проверки (нулевой аргумент userNameArman)
    @ValueSource(strings = {    //параметры теста (значения). Работает только если есть 1 аргумент (userNameArman), если больше 1 то уже НЕ подходит
      "Arman", "Gukas"
    })
    //@Order(1) Данный тест будет ПЕРВЫМ
    @Tag("SMOKE") /*Тэг @Tag("SMOKE") помечает тесты по какому-то признаку, например по смоку, по некритичным и тд*/
    /*@Disabled ("Номер бага") /*Используем эту аннотацию @Disabled, когда мы нашли дефект/баг, но
    стирать тест не будем, просто скрываем тест от проверок временно*/
    void textTest(String userNameArman) {
        System.out.println("This is a textTest");
        open("/text-box");
        $("#userName").setValue(userNameArman);
        $("#userEmail").setValue("alex@egorov.com");
        $("#currentAddress").setValue("Some address 1");
        $("#permanentAddress").setValue("Another address 1");
        executeJavaScript("$('footer').remove();");
        $("#submit").click();
        $("#output #name").shouldHave(text(userNameArman));
        $("#output #email").shouldHave(text("alex@egorov.com"));
        $("#output #currentAddress").shouldHave(text("Some address 1"));
        $("#output #permanentAddress").shouldHave(text("Another address 1"));
    }

    @Test
    //@Disabled
    //@Order(2) Данный тест будет ВТОРЫМ
    @Tags({           // Набор тэгов
        @Tag("SMOKE"),
        @Tag("afd")})
    @DisplayName("Тестирование формы регистрации студента") // Данная аннотация @DisplayName
    //передает более углубленный смысл и название теста
    void testingPracticeForm() {
        System.out.println("Practice Form IT");
        if  (!randomSubject1.equals(randomSubject2) && !randomHobby1.equals(randomHobby2)) {
            connectStatesAndCities();
            registrationPage.openPagePracticeForm()
                    .setFirstName(randomFirstName)
                    .setLastName(randomLastName)
                    .setEmail(randomEmail)
                    .setGender(randomGender)
                    .setUserNumber(randomNumber)
                    .setBirthday(randomBirthDay, randomBirthMonth, randomBirthYear)
                    .setSubject(randomSubject1, randomSubject2)
                    .setHobby(randomHobby1, randomHobby2)
                    .uploadPicture(randomPictureURL)
                    .setAddress(randomAddress)
                    .selectState(randomState)
                    .selectCity(randomCity)
                    .submitPracticeFormButton()
                    .checkPracticeForm(randomFirstName + " " + randomLastName,
                            randomEmail,
                            randomGender,
                            randomNumber,
                            randomBirthDay + " " + randomBirthMonth + "," + randomBirthYear,
                            randomSubject1 + ", " + randomSubject2,
                            randomHobby1 + ", " + randomHobby2,
                            randomPictureURL.substring(randomPictureURL.length() - 10),
                            randomAddress,
                            randomState,
                            randomCity);

        } else {
            System.out.println("we change the subjects or hobbies");
            randomSubject2 = "English";
            randomHobby2 = "Music";
            testingPracticeForm();
        }
        //new RegistrationPage().checkPracticeForm();
    }



    @DisplayName("Все на русском")
    @CsvSource(value = {            //Аннотация для параметризованных тестов если аргументов 2 и более
            "Arman,9armo9@mail.ru",
            "Gukas,lav-63@mail.ru"
    })
    @ParameterizedTest(name = "Смотри на аннотации, они здесь пригодятся. Как дела там?) Name {0} mail {1}")
    void textTes(String userNameArman, String mail) {
        System.out.println("This is a textTest");
        open("/text-box");
        $("#userName").setValue(userNameArman);
        $("#userEmail").setValue(mail);
        $("#currentAddress").setValue("Some address 1");
        $("#permanentAddress").setValue("Another address 1");
        executeJavaScript("$('footer').remove();");
        $("#submit").click();
        $("#output #name").shouldHave(text(userNameArman));
        $("#output #email").shouldHave(text(mail));
        $("#output #currentAddress").shouldHave(text("Some address 1"));
        $("#output #permanentAddress").shouldHave(text("Another address 1"));
    }





    @DisplayName("Все на русском")
    @CsvSource(value = {          //В csv разделителями считаются запятые. А что делать если мы в аргумент передаем значение с запятой внутри? Использовать delimiter, обозначение разделения аргументов. Смотри в примере, все написано.
            "Arman|9armo9@mail.ru",  //можно записывать кроме String также и дабл, инт и тд. Junit сможет запарсить
            "Gukas|lav-63@mail.ru"
    }, delimiter = '|')           //теперь пох на запятые, пиши их в значениях аргументов с кайфом.
    @CsvFileSource(resources = "/test_data/textTests.csv")                //данная аннотация то же самое что и CsvSource, только данный метод вынесен в файл в textTests.csv в resources, выносим в отдельный файл если слишком много аргументов, и писать в аннотации сверху метода уже некрасиво и некомпактно
    @ParameterizedTest(name = "Name {0} mail {1}")
    void textTests(String userNameArman, String mail) {
        System.out.println("This is a textTest");
        open("/text-box");
        $("#userName").setValue(userNameArman);
        $("#userEmail").setValue(mail);
        $("#currentAddress").setValue("Some address 1");
        $("#permanentAddress").setValue("Another address 1");
        executeJavaScript("$('footer').remove();");
        $("#submit").click();
        $("#output #name").shouldHave(text(userNameArman));
        $("#output #email").shouldHave(text(mail));
        $("#output #currentAddress").shouldHave(text("Some address 1"));
        $("#output #permanentAddress").shouldHave(text("Another address 1"));
    }


    @EnumSource(Language.class) //В скобках класс энама, если что первый тест не работает из за проблем с кодировкой. РАБОТАЕТ ТОЛЬКО С 1 АРГУМЕНТОМ КАК И ValueSource
    @ParameterizedTest
    void enumTest (Language language) {
    open("https://ru.selenide.org/");
    $$("#languages a").find(text(language.name())).click();
    $("h3").shouldHave(text(language.description));
    }








    static Stream <Arguments> methodSourceTest () {    //это метод обязательный для параметризованного теста с аннотацией @MethodSource. У нас два Arguments.of() потому что в тесте мы делаем проверку для русского языка RU (класс enum - Language), и для английского языка EN.
        return Stream.of(
               Arguments.of(Language.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
               Arguments.of(Language.RU, List.of("С чего начать?", "Док","ЧАВО" , "Блог", "Javadoc","Пользователи" ,"Отзывы"))
        );
    }

    @MethodSource
    @ParameterizedTest
    void methodSourceTest (Language language, List<String> expectedButtons) {  //в аргументах содержит Enum и List - сложные объекты в большом количестве (Аргументов больше 1)
        open("https://ru.selenide.org/");
        $$("#languages a").find(text(language.name())).click();
        $$(".main-menu-pages a").filter(visible)
                .shouldHave(texts(expectedButtons));
    }
}
