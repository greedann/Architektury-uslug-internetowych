package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Company google = new Company("Google", 100);
        Company amazon = new Company("Amazon", 50);
        Company meta = new Company("Meta", 75);

        Employee googleEmployee1 = new Employee("Segrey Brin", 10, google);
        Employee googleEmployee2 = new Employee("Larry Page", 11, google);
        Employee amazonEmployee = new Employee("Jeff Bezos", 5, amazon);
        Employee metaEmployee = new Employee("Mark Zukerberg", 7, meta);

        System.out.println(googleEmployee1);
        System.out.println(amazonEmployee);
        System.out.println(metaEmployee);

        System.out.println(google);
        System.out.println(amazon);
        System.out.println(meta);

        System.out.println(google.getCharacters());
        System.out.println(amazon.getCharacters());
        System.out.println(meta.getCharacters());

        List<Company> companies = Stream.of(google, amazon, meta)
                .collect(Collectors.toList());

        HashSet<Employee> charactersSet = companies.stream()
                .flatMap(company -> company.getCharacters().stream()).collect(Collectors.toCollection(HashSet::new));

        charactersSet.forEach(System.out::println);

        System.out.println("Sorted by name:");

        charactersSet.stream().filter(employee -> employee.company == google).sorted()
                .forEach(System.out::println);

        System.out.println("Transformed to DTO:");

        List<EmployeeDto> characterList = charactersSet.stream()
                .map(employee -> new EmployeeDto(employee.name, employee.level, employee.company.name))
                .sorted().toList();

        characterList.forEach(System.out::println);

        String filename = "file.ser";
        // Serialization
        try {
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(companies);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }

        List<Company> professionsDeserialized = null;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            professionsDeserialized = (List<Company>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");

            professionsDeserialized.forEach(System.out::println);

            professionsDeserialized.stream()
                    .flatMap(company -> company.getCharacters().stream())
                    .forEach(System.out::println);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        ForkJoinPool customThreadPool = new ForkJoinPool(2);

        customThreadPool.submit(() -> {
            System.out.println("Warrior characters:");
            companies.parallelStream()
                    .flatMap(company -> company.getCharacters().stream())
                    .filter(employee -> employee.getLevel() < 10)
                    .forEach(employee -> {
                        try {
                            Thread.sleep(1000);
                            System.out.println(employee);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
        });

        customThreadPool.shutdown();
        try {
            customThreadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}