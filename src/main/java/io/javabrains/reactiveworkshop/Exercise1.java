package io.javabrains.reactiveworkshop;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        System.out.println("Print all numbers in the intNumbersStream stream");
        StreamSources.intNumbersStream().forEach(System.out::println);

        // Print numbers from intNumbersStream that are less than 5
        System.out.println("Print numbers from intNumbersStream that are less than 5");
        StreamSources.intNumbersStream().filter(it -> it < 5).forEach(System.out::println);

        // Print the second and third numbers in intNumbersStream that's greater than 5
        System.out.println("Print the second and third numbers in intNumbersStream that's greater than 5");
        StreamSources.intNumbersStream().filter(it -> it > 5).skip(1).limit(2).forEach(System.out::println);

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1

        System.out.println("Print the first number in intNumbersStream that's greater than 5.\n" +
                " If nothing is found, print -1");

        System.out.println(StreamSources.intNumbersStream().filter(it -> it > 5).findFirst().orElse(-1));

        // Print first names of all users in userStream
        System.out.println("Print first names of all users in userStream");
        StreamSources.userStream().map(User::getFirstName).forEach(System.out::println);

        // Print first names in userStream for users that have IDs from number stream
        System.out.println("Print first names in userStream for users that have IDs from number stream");
        //1st way
        StreamSources.userStream()
                .filter(it -> StreamSources.intNumbersStream()
                        .anyMatch(number -> number == it.getId()))
                .map(user -> user.getFirstName())
                .forEach(System.out::println);

        //2nd way
        StreamSources.intNumbersStream()
                .flatMap(id -> StreamSources.userStream().filter(it -> it.getId() == id))
                .map(user -> user.getFirstName())
                .forEach(System.out::println);


    }

}
