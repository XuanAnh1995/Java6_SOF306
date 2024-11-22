package com.example.demo.session1.streamAPI;

import com.example.demo.session1.lambda.entity.Student;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {

    static List<Student> list = Arrays.asList(
            new Student("Nguyen Van An", false, 7.5),
            new Student("Nguyen Duc Tan", true, 5.5),
            new Student("Tran Van Tuan", true, 8.5),
            new Student("Le Thu Huyen", false, 9.5),
            new Student("Nguyen Duc Tien", true, 4.5)
    );

    public static void main(String[] args) {
        demo1();
        System.out.println("------------------------------------------");
        demo2();
        System.out.println("------------------------------------------");
        demo3();
        System.out.println("------------------------------------------");
        demo4();
    }

    private static void demo4() {

        double average = list.stream()
                .mapToDouble(Student::getMarks)
                .average().getAsDouble();

        System.out.println("average : " + average);

        double sum = list.stream()
                .mapToDouble(Student::getMarks)
                .sum();

        System.out.println("sum : " + sum);

        double min_marks = list.stream()
                .mapToDouble(Student::getMarks)
                .min().getAsDouble();

        System.out.println("min_marks : " + min_marks);

        boolean all_passed = list.stream()
                .allMatch(student -> student.getMarks() >= 5);

        System.out.println("all_passed : " + all_passed);

        Student min_Student = list.stream()
                .reduce(list.get(0), (min, student) -> student.getMarks() < min.getMarks() ? student : min);

        System.out.println("min_Student : " + min_Student);

    }

    private static void demo3() {
        List<Student> result = list.stream()
                .filter(student -> student.getMarks() >= 7)
                .peek(student -> student.setName(student.getName().toUpperCase()))
                .collect(Collectors.toList());

        result.forEach(student -> {
            System.out.println(">> Name: " + student.getName());
            System.out.println(">> Marks: " + student.getMarks());
            System.out.println();
        });
    }

    private static void demo2() {
        List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);

        DecimalFormat df = new DecimalFormat("#.##");

        List<Double> result = list.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> Math.sqrt(n))
//                .map(d -> Double.parseDouble(df.format(d)))   // Định dạng lấy 2 chữ số sau dấu .
//                .map(d ->Math.ceil(d*100)/100)  // Làm tròn lên đến 2 chữ số thập phân
//                .map(d -> Math.floor(d* 100)/100)   // Làm tròn xuống đến 2 chữ số thập phân
                .peek(d -> System.out.println(d))
                .collect(Collectors.toList());
    }

    private static void demo1() {
        Stream<Integer> stream = Stream.of(1, 9, 4, 7, 5, 2);
        stream.forEach(n -> {
            System.out.println(n);
        });
        System.out.println("------------------------------------------");

        List<Integer> list = Arrays.asList(1, 9, 4, 7, 5, 2);
        System.out.println("Danh sach cac so trong mang");
        list.stream().forEach((n) -> {
            System.out.println(n);
        });
    }
}
