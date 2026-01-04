package tests;

import org.junit.jupiter.api.*;

@Tag("Simple")
public class SimpleTestsGo {

    @BeforeEach
    void setResultBeforeEach() {
        System.out.println("setting result for this method");
        result = getResult();
    }

    @AfterEach
    void deleteResultAfterEach() {
        System.out.println("delete result after method");
        result = getResult();
    }

    @BeforeAll
    static void setUpAll() {
        System.out.println("This  method goes before all");


    }

    @Test
    void newTest() {
        System.out.println("Here 1 newTest");
        Assertions.assertTrue(result > 2);
    }

    @Test
    void newSecondTest() {
        System.out.println(" Here 2 newSecondTest");
        Assertions.assertTrue(result > 2);
    }

    @Test
    void newThirdTest() {
        System.out.println("Here 3 newThirdTest");
        Assertions.assertTrue(result > 2);
    }

    private int getResult() {
        return 110;
    }

    int result;
}
