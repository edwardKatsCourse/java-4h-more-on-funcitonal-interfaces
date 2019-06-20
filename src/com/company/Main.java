package com.company;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //1. collections
        //2. Stream API

        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Sarah");
        names.add("Michael");
        names.add("Peter");
        names.add("David");
        names.add("Samuel");

        Integer minimal = 5;
        Integer maximal = 6;


        //names.stream() //<SINGLE_PARAMETER>
//                .filter(asdasd -> asdasd.length() >= minimal && asdasd.length() <= maximal) //Predicate
//                .map(name -> name + " - " + name.length()) //Function: принял А - вернул Б (принял деньги, вернул товар)
//                .collect(Collectors.toList())
//                .forEach(x -> /*public void accept(T t) {*/ System.out.println(x) /*}*/); //Consumer: на вход что-то - на выход void

        names.stream() //<SINGLE_PARAMETER>

                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.length() >= minimal && s.length() <= maximal;
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String name) {
                        return name + " " + name.length();
                    }
                })

                //-----------------------
                //1 of 3
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                })

                //2 of 3
                .sorted((param1, param2) -> param1.compareTo(param2))

                //3 of 3
                .sorted(String::compareTo)
                //-----------------------

                .collect(Collectors.toList())
                .forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                });



        //System.out.println(resultNames);

        processUser(() -> System.out.println());
        processUser(new UserInterface() {
            @Override
            public void callUser() {
                System.out.println();
            }
        });

        System.out.println("-------------");

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "John Doe");
        map.put(2, "Marry Smith");

        System.out.println("Id:");
        Integer id = new Scanner(System.in).nextInt();

//        map.computeIfAbsent(id, key -> {
//            System.out.println("Name: ");
//            String name = new Scanner(System.in).nextLine();
//
//            return name;
//        });

        map.computeIfAbsent(id, new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                System.out.println("Name: ");
                String name = new Scanner(System.in).nextLine();
                return name;
            }
        });

        map.forEach((key, value) -> System.out.println(key + " " + value));

        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {
                System.out.println(integer + " " + s);
            }
        });
    }

    public static void biConsumer(Object param1, Object param2) {
    }


    public static void processUser(UserInterface user) {
        user.callUser();
    }

}

interface UserInterface {
    void callUser();
}

class User implements UserInterface {

    public void callUser() {
        System.out.println("user");
    }
}