package guru.qa.tests;

import guru.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;


import static java.lang.String.format;

public class RegistrationFormWithFakerTests extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = "Other";
    String mobile = faker.phoneNumber().subscriberNumber(10);
    String day = faker.number().numberBetween(1, 30) + "";
    String month = "July"; // не смогла определить месяц через Faker
    String year = faker.number().numberBetween(1950, 2000) + "";
    String hobbie = "Music";
    String file = "Floppa.png";
    String address = faker.address().fullAddress().substring(7);
    String state = "NCR";
    String city = "Delhi";


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