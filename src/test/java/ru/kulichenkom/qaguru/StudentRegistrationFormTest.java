package ru.kulichenkom.qaguru;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    //Элементы формы регистрации
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmail = $("#userEmail"),
            genderMaleRadio = $("#genterWrapper").$(byText("Male")),
            genderFemaleRadio = $("#genterWrapper").$(byText("Female")),
            genderOtherRadio = $("#genterWrapper").$(byText("Other")),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            monthSelect = $(".react-datepicker__month-select"),
            yearSelect = $(".react-datepicker__year-select"),
            subjectsInput = $("#subjectsInput"),
            hobbySportsCheckbox = $("#hobbiesWrapper").$(byText("Sports")),
            hobbyReadingCheckbox = $("#hobbiesWrapper").$(byText("Reading")),
            hobbyMusicCheckbox = $("#hobbiesWrapper").$(byText("Music")),
            uploadPicture = $("#uploadPicture"),
            currentAddressTextArea = $("#currentAddress"),
            state = $("#state"),
            stateDropDownList = $("#react-select-3-input"),
            city = $("#city"),
            cityDropDownList = $("#react-select-4-input"),
            submitButton = $("#submit");
    private ElementsCollection days = $$(".react-datepicker__day");

    //Элементы формы подтверждения регистрации
    private SelenideElement modalHeader = $(".modal-header");
    private ElementsCollection tableRows = $("tbody").$$("tr");



    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillRegistrationFormTest() {
        open("/automation-practice-form");
        firstNameInput.setValue("Ivan");
        lastNameInput.setValue("Ivanov");
        userEmail.setValue("IvanovIvan654894@gmail.com");
        genderMaleRadio.click();
        userNumberInput.setValue("9874546546");
        dateOfBirthInput.click();
        monthSelect.selectOption("April");
        yearSelect.selectOption("2013");
        days.findBy(text("30")).click();
        subjectsInput.setValue("Chemistry").pressEnter();
        subjectsInput.setValue("Arts").pressEnter();
        hobbySportsCheckbox.click();
        hobbyReadingCheckbox.click();
        hobbyMusicCheckbox.click();
        uploadPicture.uploadFromClasspath("img/chil.jpg");
        currentAddressTextArea.setValue("Saint-Petersburg, Nevsky prospect 1");
        state.click();
        stateDropDownList.setValue("Ha").pressEnter();
        city.click();
        cityDropDownList.setValue("Ka").pressEnter();
        submitButton.click();

        modalHeader.shouldHave(text("Thanks for submitting the form"));
        tableRows.findBy(text("Student Name")).shouldHave(text("Ivan Ivanov"));
        tableRows.findBy(text("Student Email")).shouldHave(text("IvanovIvan654894@gmail.com"));
        tableRows.findBy(text("Gender")).shouldHave(text("Male"));
        tableRows.findBy(text("Mobile")).shouldHave(text("9874546546"));
        tableRows.findBy(text("Date of Birth")).shouldHave(text("30 April,2013"));
        tableRows.findBy(text("Subjects")).shouldHave(text("Chemistry, Arts"));
        tableRows.findBy(text("Hobbies")).shouldHave(text("Sports, Reading, Music"));
        tableRows.findBy(text("Picture")).shouldHave(text("chil.jpg"));
        tableRows.findBy(text("Address")).shouldHave(text("Saint-Petersburg, Nevsky prospect 1"));
        tableRows.findBy(text("State and City")).shouldHave(text("Haryana Karnal"));
    }
}
