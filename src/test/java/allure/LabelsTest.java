package allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {

    //это смотрим в аллюр в behaviors РЕКОМЕНДУЕМЫЙ СПОСОБ
    @Test
    @Feature("Issue в репозитории")  //главный уровень в названии в аллюр
    @Story("Создание Issue") //подуровень в названии аллюр
    @Owner("Arman Gukasyan")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")
    public void testStaticLabels () {

    }

    //То же самое что и первый способ. НЕ рекомендуется. Почему динамический? Потому что внутри метода можно изменить поведение кода, например if else - если правда то поставь 1 название, если нет то 2 название - динамические названия, могут меняться
    @Test
    public void testDynamicLabels() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание Issue для авторизованного пользователя")
        );
        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue");
        Allure.label("owner", "Arman Gukasyan");
        Allure.label("severity", SeverityLevel.BLOCKER.value());
        Allure.link("testing", "https://testing.github.com");
    }
}
