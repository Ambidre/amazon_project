package gmail.anastasiacoder.tests;

import com.github.javafaker.Faker;

public class TestData {
    public static Faker faker = new Faker();
    public static String email = faker.name().username() + "@test.com";
}