import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1280x1024";
//        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void setUp() {
        closeWebDriver();
    }

}
