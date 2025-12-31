package tests;

import java.util.*;

public class ExercisesForJava {

    public static void main(String[] args) {

        // массив данных
        String [] massive = new String[] {"Haier", "LG", "Samsung"};
        for (String s : massive) {
            System.out.println(s);
        }

        // список строк (улучшенная версия массива)
        List <String> stringsArray = new ArrayList<>();
        stringsArray.add("firstString");
        stringsArray.add("secondString");
        System.out.println(stringsArray);

        // уникальный список строк, НЕ выводит дубликаты, только 1 раз
        // (улучшенная версия array)
        Set <String> uniqueArray = new HashSet<>();
        uniqueArray.add("Hi");
        uniqueArray.add("Hello");
        uniqueArray.add("Hi there");
        uniqueArray.add("Hii");
        uniqueArray.add("Himars");
        System.out.println(uniqueArray);

        // ключ - значение
        Map <String, String> dataBase = new HashMap<>();
        dataBase.put("daobaebik", "sanik");
        dataBase.put("daobaeb", "san");
        // есть 3 способа вывода через циклы
        // 1-ый: вывод всех пар - ключ и значение
        for (Map.Entry<String, String> entry : dataBase.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        // 2-ой: вывод по ключу
        for (String key : dataBase.keySet()){
            System.out.println(key);
        }
        // 3-ий: вывод по значению
        for (String value : dataBase.values()){
            System.out.println(value);
        }

        ExercisesForJava ex = new ExercisesForJava();
        ex.massiveSortMinToMax();

    }

    public void from1to10 () {
        for (int j = 1; j <= 10 ; j++) {
            System.out.println(j);
        }
    }

    public void sum1to100 () {
        int sum = 0;
        for (int i = 1; i <= 100 ; i++) {
        sum += i;
        }
        System.out.println(sum);
    }

    public void factorialForNum () {
        int sum = 1;
        for (int i = 1; i <= 10 ; i++) {
            sum *= i;
        }
        System.out.println(sum);
    }

    public void massiveSortMinToMax () {
        int [] numbers = {535,333,4156,1135,23};
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        System.out.println(min);
    }

}
