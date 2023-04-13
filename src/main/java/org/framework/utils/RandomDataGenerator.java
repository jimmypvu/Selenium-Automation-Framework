package org.framework.utils;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomDataGenerator {
    public static Faker faker = new Faker();
    public static Random random = new Random();
    public static String[] topDomainArr = {".com", ".net", ".org", ".io", ".co", ".ai", ".dev", ".me"};
    public static String[] streetsArr = {"AVE", "ST", "RD", "DR", "CT", "CIR", "BLVD", "CTR", "HWY", "PKWY", "PT", "SHR", "WAY"};

    public static String getRandomFor(DataType type){
        switch(type){
            case FIRSTNAME: return faker.name().firstName();
            case LASTNAME: return faker.name().lastName();
            case FULLNAME: return faker.name().fullName();
            case EMAIL: return getRandomEmail();
            case CITY: return faker.address().city();
            case ADDRESS: return getRandomAddress();
            case USERNAME: return getRandomAlphanumeric(getIntBetween(7, 15));
            default: return "NoSuchType";
        }
    }

    public static String getRandomEmail(){
        String email = getRandomAlphanumeric(random.nextInt(16) + 4);
        String secDomain = getRandomAlphabetic(random.nextInt(12) + 3);
        return email + "@" + secDomain + topDomainArr[random.nextInt(topDomainArr.length)];
    }

    public static String getRandomAddress(){
        String street = getRandomAlphabetic(random.nextInt(12) + 4);
        return getRandomNumber(random.nextInt(4) + 1) + " " + street + " " + streetsArr[random.nextInt(streetsArr.length)];
    }

    public static String getRandomAlphanumeric(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String getRandomAlphabetic(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String getRandomNumber(int length){
        return faker.number().digits(length);
    }

    public static int getIntBetween (int min, int max){
        return faker.number().numberBetween(min, max);
    }

    public static String getRandomString(int n, boolean abc, boolean nums, boolean special){
        String chars = "";
        if(abc){
            chars += "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if(nums){
            chars += "0123456789";
        }
        if(special){
            chars += "~!@#$%^&*()_-+=[]{}\\|:;'\"<>,./?";
        }

        StringBuilder sb = new StringBuilder(n);
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }

        return sb.toString();
    }

    public static String getRandomString(int n){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^&*()_-+=[]{}\\|:;'\"<>,./?";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }
}
