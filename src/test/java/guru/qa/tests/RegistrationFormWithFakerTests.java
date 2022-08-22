package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import java.util.Locale;

import static java.lang.String.format;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormWithFakerTests extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();
    String firstName, lastName, email, gender, mobile, day, month, year, hobbie, file, address, state, city;

    @BeforeEach
    void prepareTestData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        gender = "Other";
        mobile = faker.phoneNumber().subscriberNumber(10);
        day = faker.number().numberBetween(1, 30) + "";
        month = "July"; // не смогла определить месяц через Faker
        year = faker.number().numberBetween(1950, 2000) + "";
        hobbie = "Music";
        file = "Floppa.png";
        address = faker.address().fullAddress().substring(7);
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

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", format("%s %s", firstName, lastName))
                .checkResult("Student Email", email)
                .checkResult("Gender", gender)
                .checkResult("Mobile", mobile)
                .checkResult("Date of Birth", format("%s %s,%s", day, month, year))
                .checkResult("Hobbies", hobbie)
                .checkResult("Picture", file)
                .checkResult("Address", address)
                .checkResult("State and City", format("%s %s", state, city));

    }

}