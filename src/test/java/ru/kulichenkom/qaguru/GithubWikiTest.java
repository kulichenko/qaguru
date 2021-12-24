package ru.kulichenkom.qaguru;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GithubWikiTest {

    SelenideElement
            wikiTab = $("#wiki-tab"),
            authorName = $(".author"),
            wikiPageHeader = $(".markdown-body h1"),
            softAssertionsLink = $(byText("Soft assertions")),
            softAssertionsPageHeader = $(".gh-header-title"),
            codeBlocks = $(".markdown-body");

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void githubTest() {
        open("/selenide/selenide");
        authorName.shouldHave(text("selenide"));
        wikiTab.click();
        wikiPageHeader.shouldHave(text("Welcome to the selenide wiki!"));
        softAssertionsLink.click();
        softAssertionsPageHeader.shouldHave(text("SoftAssertions"));
        codeBlocks.shouldHave(text("Using JUnit5 extend test class:"));
    }
}
