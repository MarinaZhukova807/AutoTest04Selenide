package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CallBackTest {

    @BeforeEach
    void openApplication(){
        open("http://localhost:9999/");
    }
    @Test
    void shouldSubmitRequest(){
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue("Калуга");
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue("25.08.2022");
        //form.$(".calendar__day_state_current").click();
        form.$("[data-test-id=name] input").setValue("Иван Иванов");
        form.$("[data-test-id=phone] input").setValue("+79991234567");
        form.$("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification] .notification__title").shouldBe(visible,Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__title").shouldHave(exactText("Успешно!"));
    }

}
