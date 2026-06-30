import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static config.Routes.*;
import static testdata.TestData.*;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    void shouldSubmitWithValidData() {
        open(AUTOMATION_PRACTICE_FORM);

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(validUserEmail);
        $("#genterWrapper").$$("label").findBy(text(userGender)).click();
        $("#userNumber").setValue(validUserMobileNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__year-select").selectOption(birthYear);
        $(".react-datepicker__day--0" + birthDay).click();
        $("#subjectsInput").setValue(subjectValue).pressEnter();
        $("#hobbiesWrapper").$$("label").findBy(text(hobbyValue)).click();
        $("#uploadPicture").uploadFromClasspath(uploadPictureName);
        $("#currentAddress").setValue(userAddress);
        $("#react-select-3-input").setValue(stateOption).pressEnter();
        $("#react-select-4-input").setValue(cityOption).pressEnter();
        $("#submit").click();

        $$("tbody tr").findBy(text(studentNameKey)).shouldHave(text(studentFullName));
        $$("tbody tr").findBy(text(studentEmailKey)).shouldHave(text(validUserEmail));
        $$("tbody tr").findBy(text(studentGenderKey)).shouldHave(text(userGender));
        $$("tbody tr").findBy(text(userMobileNumberKey)).shouldHave(text(validUserMobileNumber));
        $$("tbody tr").findBy(text(dateOfBirthKey)).shouldHave(text(fullDateOfBirth));
        $$("tbody tr").findBy(text(SubjectsKey)).shouldHave(text(subjectValue));
        $$("tbody tr").findBy(text(hobbiesKey)).shouldHave(text(hobbyValue));
        $$("tbody tr").findBy(text(pictureKey)).shouldHave(text(uploadPictureName));
        $$("tbody tr").findBy(text(addressKey)).shouldHave(matchText(userAddress));
        $$("tbody tr").findBy(text(stateAndCityKey)).shouldHave(text(stateAndCityValue));
    }

    @Test
    void shouldSubmitWithValidRequiredFields() {
        open(AUTOMATION_PRACTICE_FORM);

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$$("label").findBy(text(userGender)).click();
        $("#userNumber").setValue(validUserMobileNumber);
        $("#submit").click();

        $$("tbody tr").findBy(text("Student Name")).shouldHave(text(studentFullName));
        $$("tbody tr").findBy(text("Student Email")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("Gender")).shouldHave(text(userGender));
        $$("tbody tr").findBy(text("Mobile")).shouldHave(text(validUserMobileNumber));
        $$("tbody tr").findBy(text("Date of Birth")).shouldNotBe(empty);
        $$("tbody tr").findBy(text("Subjects")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("Hobbies")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("Picture")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("Address")).$("td", 1).shouldBe(empty);
        $$("tbody tr").findBy(text("State and City")).$("td", 1).shouldBe(empty);
    }

    @Test
    void shouldNotSubmitWithoutFirstName() {
        open(AUTOMATION_PRACTICE_FORM);

        $("#lastName").setValue(lastName);
        $("#genterWrapper").$$("label").findBy(text(userGender)).click();
        $("#userNumber").setValue(validUserMobileNumber);
        $("#submit").click();
        $(".modal-content").shouldNot(exist);
        $("#firstName").shouldHave(attribute("required"));
        $("#firstName").shouldBe(empty);
    }

    @Test
    void shouldNotSubmitWithoutLastName() {
        open(AUTOMATION_PRACTICE_FORM);

        $("#firstName").setValue(firstName);
        $("#genterWrapper").$$("label").findBy(text(userGender)).click();
        $("#userNumber").setValue(validUserMobileNumber);
        $("#submit").click();
        $(".modal-content").shouldNot(exist);
        $("#lastName").shouldHave(attribute("required"));
        $("#lastName").shouldBe(empty);
    }

    @Test
    void shouldNotSubmitWithoutGender() {
        open(AUTOMATION_PRACTICE_FORM);

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userNumber").setValue(validUserMobileNumber);
        $("#submit").click();
        $(".modal-content").shouldNot(exist);
        $("#gender-radio-1").shouldHave(attribute("required"));
        $("#gender-radio-2").shouldHave(attribute("required"));
        $("#gender-radio-3").shouldHave(attribute("required"));
        $("#gender-radio-1").shouldNot(selected);
        $("#gender-radio-2").shouldNot(selected);
        $("#gender-radio-3").shouldNot(selected);
    }

    @Test
    void shouldNotSubmitWithoutMobileNumber() {
        open(AUTOMATION_PRACTICE_FORM);

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#gender-radio-1").click();
        $("#submit").click();
        $(".modal-content").shouldNot(exist);
        $("#userNumber").shouldHave(attribute("required"));
        $("#userNumber").shouldBe(empty);
    }

    @Test
    void shouldNotSubmitWithWrongMobileNumber() {
        open(AUTOMATION_PRACTICE_FORM);

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#gender-radio-1").click();
        $("#userNumber").setValue(invalidUserMobileNumber);
        $("#submit").click();
        $(".modal-content").shouldNot(exist);
        $("#userNumber").shouldHave(attribute("required"));
        $("#userNumber").shouldHave(value(invalidUserMobileNumber));
    }
}