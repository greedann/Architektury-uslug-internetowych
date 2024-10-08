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
        Profession warrior = new Profession("Warrior", 100);
        Profession mage = new Profession("Mage", 50);
        Profession rogue = new Profession("Rogue", 75);

        Character warriorCharacter = new Character("Warrior", 10, warrior);
        Character warriorCharacter2 = new Character("Warrior2", 10, warrior);
        Character mageCharacter = new Character("AMage", 5, mage);
        Character rogueCharacter = new Character("ARogue", 7, rogue);

        System.out.println(warriorCharacter);
        System.out.println(mageCharacter);
        System.out.println(rogueCharacter);

        System.out.println(warrior);
        System.out.println(mage);
        System.out.println(rogue);

        System.out.println(warrior.getCharacters());
        System.out.println(mage.getCharacters());
        System.out.println(rogue.getCharacters());

        List<Profession> professions = Stream.of(warrior, mage, rogue)
                .collect(Collectors.toList());

        HashSet<Character> CharactersSet = professions.stream()
                .flatMap(profession -> profession.getCharacters().stream())
                .collect(Collectors.toList()).stream().collect(Collectors.toCollection(HashSet::new));

        CharactersSet.stream().forEach(System.out::println);

        System.out.println("Sorted by name:");

        CharactersSet.stream().filter(character -> character.profession == warrior).sorted()
                .forEach(System.out::println);

        System.out.println("Transformed to DTO:");

        // Using single Stream API pipeline transform elements collection created
        // earlier into
        // steam of DTO objects, then sort them using natural order and collect them
        // into
        // List collection. Then using second pipeline print it. (1 point)

        List<CharacterDto> characterList = CharactersSet.stream()
                .map(character -> new CharacterDto(character.name, character.level, character.profession.name))
                .sorted().collect(Collectors.toList());

        characterList.stream().forEach(System.out::println);

        String filename = "file.ser";
        // Serialization
        try {
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(professions);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }

        List<Profession> professionsDeserialized = null;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            professionsDeserialized = (List<Profession>) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");

            professionsDeserialized.stream().forEach(System.out::println);

            professionsDeserialized.stream()
                    .flatMap(profession -> profession.getCharacters().stream())
                    .forEach(System.out::println);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        // Using Stream API parallel pipelines with custom thread pool execute some task
        // on
        // each category. For example task can be printing each collection elements with
        // intervals using Thread.sleep() to simulate workload. Observer result with
        // different custom pool sizes. For thread pool use ForkJoinPool Remember about
        // closing the thread pool. (2 points)

        ForkJoinPool customThreadPool = new ForkJoinPool(2);

        customThreadPool.submit(() -> {
            System.out.println("Warrior characters:");
            professions.parallelStream()
                    .flatMap(profession -> profession.getCharacters().stream())
                    .filter(character -> character.name.startsWith("A"))
                    .forEach(character -> {
                        try {
                            Thread.sleep(1000);
                            System.out.println(character);
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