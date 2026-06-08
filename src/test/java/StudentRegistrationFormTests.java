import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    void shouldSubmitWithValidData() {
        open("/automation-practice-form");

        $("[id=firstName]").setValue("John");
        $("[id=lastName]").setValue("Doe");
        $("[id=userEmail]").setValue("some-male@example.com");
        $("#gender-radio-1").click();
        $("#userNumber").setValue("7479990002");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--018").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbies-checkbox-1").click();
//        $("#uploadPicture").uploadFile(new File("src/test/resources/client_rights_on_red.png"));
        $("#uploadPicture").uploadFromClasspath("client_rights_on_red.png");
        $("#currentAddress").setValue("Проснись, John Doe, ты надежда мира!\n" +
                "Я понял, что всё это время не у того просил\n" +
                "Собака больше не хочет корма, все спокойно, хозяева живы, пока\n" +
                "Случайно порезанный палец и кровь не напомнят щенку, что он сын волка");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();
        //        System.out.println($$("tbody tr").texts());
        $$("tbody tr").findBy(text("Student Name")).shouldHave(text("John Doe"));
//        $$("tbody tr").findBy(text("Student Name")).shouldHave(text("Male")); // test fail check
        $$("tbody tr").findBy(text("Student Email")).shouldHave(text("some-male@example.com"));
        $$("tbody tr").findBy(text("Gender")).shouldHave(text("Male"));
        $$("tbody tr").findBy(text("Mobile")).shouldHave(text("7479990002"));
        $$("tbody tr").findBy(text("Date of Birth")).shouldHave(text("18 July,2000"));
        $$("tbody tr").findBy(text("Subjects")).shouldHave(text("Maths"));
        $$("tbody tr").findBy(text("Hobbies")).shouldHave(text("Sports"));
        $$("tbody tr").findBy(text("Picture")).shouldHave(text("client_rights_on_red.png"));
        $$("tbody tr").findBy(text("Address")).shouldHave(matchText("Проснись, John Doe.*, все спокойно,.*,что он сын волка"));
        $$("tbody tr").findBy(text("State and City")).shouldHave(text("Haryana Karnal"));
    }
    @Test
    void shouldSubmitWithValidRequiredFields() {
        open("/automation-practice-form");

        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
//        $("[id=userEmail]").setValue("some-male@example.com"); // test fail check
        $("#gender-radio-1").click();
        $("#userNumber").setValue("7479990002");
        $("#submit").click();
//        System.out.println($$("tbody tr").texts());
        $$("tbody tr").findBy(text("Student Name")).shouldHave(text("John Doe"));
        $$("tbody tr").findBy(text("Student Email")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("Gender")).shouldHave(text("Male"));
        $$("tbody tr").findBy(text("Mobile")).shouldHave(text("7479990002"));
        $$("tbody tr").findBy(text("Date of Birth")).shouldHave(text("08 June,2026"));
        $$("tbody tr").findBy(text("Subjects")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("Hobbies")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("Picture")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("Address")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("State and City")).$("td", 1).shouldBe(empty);
    }
}
