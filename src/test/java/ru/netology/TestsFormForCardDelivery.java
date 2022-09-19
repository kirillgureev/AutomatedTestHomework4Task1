package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class TestsFormForCardDelivery {
    // Метод для генерации даты
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldFilledForm() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        String planningDate = generateDate(4);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Кирилл Гуреев");
        $("[data-test-id='phone'] input").setValue("+79106231125");
        $("[role='presentation']").click();
        $(".button__content").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldFilledFormWithDashInName() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        String planningDate = generateDate(4);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Кирилл-Кирилл Гуреев");
        $("[data-test-id='phone'] input").setValue("+79106231125");
        $("[role='presentation']").click();
        $(".button__content").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
