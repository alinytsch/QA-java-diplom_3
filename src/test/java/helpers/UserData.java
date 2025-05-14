package helpers;

import com.github.javafaker.Faker;

public class UserData {
    private static final Faker faker = new Faker();

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomName() {
        return faker.name().firstName();
    }

    public static String randomPassword() {
        return faker.internet().password(6, 10);
    }
}