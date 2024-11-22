package com.example.demo.session1.lambda;

import com.example.demo.session1.lambda.entity.Student;

import java.util.*;

public class Lambda {

    static List<Student> list = Arrays.asList(
            new Student("Nguyen Van An", false, 7.5),
            new Student("Nguyen Duc Tan", true, 5.5),
            new Student("Tran Van Tuan", true, 8.5),
            new Student("Le Thu Huyen", false, 9.5),
            new Student("Nguyen Duc Tien", true, 4.5)
    );

    public static void main(String[] args) {
        demo1();
        System.out.println("----------------------------------------------------");
        demo2();
        System.out.println("----------------------------------------------------");
        demo3();
        System.out.println("----------------------------------------------------");
        demo4();
        System.out.println("----------------------------------------------------");
        demo5();

    }

    private static void demo5() {
//        Demo5Inter o = new Demo5Inter() {
//            @Override
//            public void m1(int x) {
//                System.out.println(x);
//            }
//        };

        Demo5Inter o = (x) -> {
            System.out.println(x);
        };
        o.m1(2024);
    }

    private static void demo4() {
        Collections.sort(list, (st1, st2) -> {
            return st1.getMarks().compareTo(st2.getMarks());
        });
        System.out.println("Danh sach student duoc sap xep theo Marks");

        list.forEach(student -> {
            System.out.println(">> Name: " + student.getName());
            System.out.println(">> Marks: " + student.getMarks());
            System.out.println();
        });
    }

    private static void demo3() {
        list.forEach(student -> {
            System.out.println(">> Name: " + student.getName());
            System.out.println(">> Marks: " + student.getMarks());
            System.out.println();
        });
    }

    private static void demo2() {
        List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);
        System.out.println("Danh sach cac sa sap xep theo thu tu lon dan");
//        list.sort((o1,o2)->{
//            return o1.compareTo(o2);
//        });
//        list.forEach((n) -> {
//            System.out.println(n);
//        });

        list.sort(Integer::compareTo);
        list.forEach(System.out::println);
    }

    private static void demo1() {
        List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);
        System.out.println("Danh sach cac so trong mang");
        list.forEach((n) -> {
            System.out.println(n);
        });
    }
}

@FunctionalInterface
interface Demo5Inter {
    void m1(int x);

    default void m2() {
    }

    public static void m3() {
    }
}