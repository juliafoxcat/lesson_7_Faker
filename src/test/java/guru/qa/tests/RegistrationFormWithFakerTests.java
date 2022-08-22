package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormWithPageObjectsTests {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    private static final String firstName, lastName, email, gender, mobile, day, month, year, hobbie, file, address, state, city;

    static {
        firstName = "Iuliia";
        lastName = "Kudrina";
        email = "rrr@mail.com";
        gender = "Other";
        mobile = "1234567890";
        day = "15";
        month = "July";
        year = "1997";
        hobbie = "Music";
        file = "Floppa.png";
        address = "My address";
        state = "NCR";
        city = "Delhi";
    }

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }


    @Test
    void fillFormTest() {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setNumber(mobile)
                .setBirthDate(day, month, year)
                .setHobbies(hobbie)
                .setFile(file)
                .setAddress(address)
                .setPlace(state, city)
                .submitForm();
//        $("#subjectsInput").setValue("Math").pressEnter();
//        $("#uploadPicture").uploadFromClasspath("1.png");
//        $("#currentAddress").setValue("Some address 1");
//        $("#state").click();
//        $("#stateCity-wrapper").$(byText("NCR")).click();
//        $("#city").click();
//        $("#stateCity-wrapper").$(byText("Delhi")).click();
//        $("#submit").click();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", day + " " + month + "," + year)
                .checkResult("Hobbies", hobbie)
                .checkResult("Picture", file)
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);

    }

}