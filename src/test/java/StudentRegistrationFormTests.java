import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    void addDataTest() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("John");
        $("[id=lastName]").setValue("Doe");
        $("[id=userEmail]").setValue("some-male@example.com");
        $("#gender-radio-1").click();
        $("#userNumber").setValue("7479990002");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $("body").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
//        $("#hobbies-checkbox-1").click();
//
//        $("#currentAddress").setValue("Проснись, John Doe, ты надежда мира!\n" +
//                "Я понял, что всё это время не у того просил\n" +
//                "Собака больше не хочет корма, все спокойно, хозяева живы, пока\n" +
//                "Случайно порезанный палец и кровь не напомнят щенку, что он сын волка");
    }

//    @Test
//    void wrongPasswordAuthorizationByEntTest() {
//        open("https://qa-guru.github.io/one-page-form/login.html");
//
//        $("[data-testid=login-input]").setValue("user1");
//        $("[data-testid=password-input]").setValue("WRONG PASSWORD").pressEnter();
//        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
//    }
//
//    @Test
//    void emptyDataTest() {
//        open("https://qa-guru.github.io/one-page-form/login.html");
//        $("[data-testid=login-input]").setValue("");
//        $("[data-testid=password-input]").setValue("");
//        $("[data-testid=submit-button]").click();
//        $("[data-testid=error-message]").shouldHave(text("Login and password are required (minimum 3 and 6 characters)"));
//    }
//
//    @Test
//    void emptyLoginTest() {
//        open("https://qa-guru.github.io/one-page-form/login.html");
//        $("[data-testid=login-input]").setValue("");
//        $("[data-testid=password-input]").setValue("salam");
//        $("[data-testid=submit-button]").click();
//        $("[data-testid=error-message]").shouldHave(text("Login is required (minimum 3 characters)"));
//    }
//
//    @Test
//    void emptyPassTest() {
//        open("https://qa-guru.github.io/one-page-form/login.html");
//        $("[data-testid=login-input]").setValue("sabr");
//        $("[data-testid=password-input]").setValue("");
//        $("[data-testid=submit-button]").click();
//        $("[data-testid=error-message]").shouldHave(text("Password is required (minimum 6 characters)"));
//    }
//
//    @Test
//    void shortPassTest() {
//        open("https://qa-guru.github.io/one-page-form/login.html");
//        $("[data-testid=login-input]").setValue("sabr");
//        $("[data-testid=password-input]").setValue("");
//        $("[data-testid=submit-button]").click();
//        $("[data-testid=error-message]").shouldHave(text("Password is required (minimum 6 characters)"));
//    }
}
