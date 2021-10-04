package com.iljasstan;

import com.codeborne.selenide.Condition;
import com.iljasstan.Enums.Welders;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class TestsParametrized {
    @CsvSource(value = {"Иванов Иван Иванович", "Иванов Петр Сергеевич"})
    @ParameterizedTest(name = "Check results about {0} and {1}")
    void testWithCSVParameters(String name) {
        open("http://www.naks.ru/assp/reestrperson/");
        $("[name='arrFilter_ff[NAME]']").setValue(name);
        $("[name='set_filter']").click();
        $(".tabl").shouldHave(Condition.text(name));
    }

    @EnumSource(value = Welders.class, names = {"WELDER3"}, mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest(name = "Check results about {0}, then about {1}")
    void testWithEnumParameters(Welders welder) {
        open("http://www.naks.ru/assp/reestrperson/");
        $("[name='arrFilter_ff[NAME]']").setValue(welder.getDesc());
        $("[name='set_filter']").click();
        $(".tabl").shouldHave(Condition.text(welder.getDesc()));
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(
                        "Москва", "Санкт-Петербург", "12.12.2021", "18.12.2021"
                ),
                Arguments.of(
                        "Москва", "Сочи", "12.12.2021", "18.12.2021"
                ),
                Arguments.of(
                        "Москва", "Казань", "12.12.2021", "18.12.2021"
                )
        );
    }

    @MethodSource("dataProvider")
    @ParameterizedTest(name = "Check cities after searching tickets")
    void testWithMethodSource(String cityFrom, String cityTo, String dateOut, String dateBack) {
        open("https://www.tutu.ru");
        $("[name=city_from]").setValue(cityFrom);
        $("[name=city_to]").setValue(cityTo).pressEnter();
        $("[name=date_from]").setValue(dateOut);
        $("[name=date_back]").setValue(dateBack).pressEnter();
        $("#root").shouldHave(Condition.text(cityFrom));
        $("#root").shouldHave(Condition.text(cityTo));
    }
}
