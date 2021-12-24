package ru.kulichenkom.qaguru;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTest {
    SelenideElement
            blockA = $("#column-a"),
            blockB = $("#column-b");

    @Test
    void dragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("h3").shouldHave(text("Drag and Drop"));
        blockA.dragAndDropTo(blockB);
        blockA.$("header").shouldHave(text("B"));
        blockB.$("header").shouldHave(text("A"));
    }
}
