package util;

import java.util.concurrent.ThreadLocalRandom;

public class UtilsForRandom {

    /*Это класс с методами рандомов на все случаи жизни для тестов*/

    public static int getRandomInt (int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static long getRandomLong (long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }

    public String randomGender (){
        String [] genders = {"Male", "Female", "Other"};
        return getRandomIndexFromArray(genders);
    }

    public static String getRandomIndexFromArray (String [] array) {
       int index = getRandomInt(0, array.length - 1);
       return array[index];
    }

    public String randomUserNumber () {
        return String.valueOf(getRandomLong(1000000000,9999999999L));

    }

   /* public String randomBirthDay () {
        String [] daysInMonth =
    }*/

    public String randomMonth (){
        String [] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return getRandomIndexFromArray(months);
    }

    public String randomYear (){
        String [] years = {"2024", "2020", "2010", "2005", "2001", "2000",
                "1999", "1993", "1991", "1980", "1950", "1920"};
        return getRandomIndexFromArray(years);
    }

    public String randomDay (){
        String [] days = {"1", "6", "9", "10", "11", "19", "20", "21", "25"};
        return getRandomIndexFromArray(days);
    }

    public String randomSubjects (){
        String [] subjects = {"Biology", "Arts", "Maths", "Accounting", "Social Studies"};
        return getRandomIndexFromArray(subjects);
    }

    public String randomHobbies (){
        String [] hobbies = {"Sports", "Reading"};
        return getRandomIndexFromArray(hobbies);
    }

    public String randomPictureURLs (){
        String [] pictureURLs = {"C:\\Users\\lukas\\flowers.jpg", "C:\\Users\\lukas\\flowerd.jpg", "C:\\Users\\lukas\\flowert.jpg"};
        return getRandomIndexFromArray(pictureURLs);
    }

    public String randomState (){
        String [] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return getRandomIndexFromArray(states);
    }


    public String randomCitiesForNCR (){
        String [] citiesForNCR = {"Delhi", "Gurgaon", "Noida"};
        return getRandomIndexFromArray(citiesForNCR);
    }
    public String randomCitiesForUttarPradesh (){
        String [] citiesForUttarPradesh = {"Agra", "Lucknow", "Merrut"};
        return getRandomIndexFromArray(citiesForUttarPradesh);
    }
    public String randomCitiesForHaryana (){
        String [] citiesForHaryana = {"Karnal", "Panipat"};
        return getRandomIndexFromArray(citiesForHaryana);
    }
    public String randomCitiesForRajasthan (){
        String [] citiesForRajasthan = {"Jaipur", "Jaiselmer"};
        return getRandomIndexFromArray(citiesForRajasthan);
    }

}

