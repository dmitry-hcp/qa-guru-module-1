import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static config.Routes.*;
import static testdata.TestData.*;

public class TextBoxTest extends TestBase {

    @BeforeEach
    void openTextBoxForm() {
        open(TEXT_BOX);
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                """);
    }

    @Test
    void shouldSubmitSimpleFormSWithValidData() {
        $("#userName").setValue(studentFullName);
        $("#submit").click();
        $("#name").shouldHave(text("Name:" + studentFullName));
        $("#email").shouldNot(exist);
        $("#output #currentAddress").shouldNot(exist);
        $("#output #permanentAddress").shouldNot(exist);

    }

    @Test
    void shouldNotSubmitSimpleFormSWithWrongEmail() {

        $("#userName").setValue(studentFullName);
        $("#submit").click();
        $("#userName").setValue(studentFullName);
        $("#userEmail").setValue(invalidUserEmail);
        $("#submit").click();
        $("#name").shouldHave(text("Name:" + studentFullName));
        $("#email").shouldNot(exist);
        $("#output #currentAddress").shouldNot(exist);
        $("#output #permanentAddress").shouldNot(exist);
    }
}
