package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class TestsFormForCardDelivery {

    @Test
    void shouldFilledForm() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $x("//input[@placeholder='Дата встречи']").setValue("20.09.2022");
        $("[data-test-id='name'] input").setValue("Кирилл Гуреев");
        $("[data-test-id='phone'] input").setValue("+79106231125");
        $("[role='presentation']").click();
        $(".button__content").click();
        $x("//div[text()= 'Успешно!']").should(Condition.visible, Duration.ofSeconds(15));
        $x("//div[contains(text(), 'Встреча успешно забронирована')]").should(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldFilledFormWithDashInName() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").setValue("20.09.2022");
        $("[data-test-id='name'] input").setValue("Кирилл-Кирилл Гуреев");
        $("[data-test-id='phone'] input").setValue("+79106231125");
        $("[role='presentation']").click();
        $(".button__content").click();
        $x("//div[text()= 'Успешно!']").should(Condition.visible, Duration.ofSeconds(15));
        $x("//div[contains(text(), 'Встреча успешно забронирована')]").should(Condition.visible, Duration.ofSeconds(15));
    }
}
