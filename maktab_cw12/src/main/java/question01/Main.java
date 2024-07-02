package question01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Alice", 30),
                new Person("Aob", 25),
                new Person("Anna", 35),
                new Person("Alex", 40),
                new Person("Aavid", 28));
        //part 1
        int ageSum = people.stream().mapToInt(Person::getAge).sum();

        Predicate<Person> predicate = person -> person.getName().startsWith("A");

        //part 2
        int ageThatStartsWith = people.stream()
                .filter(predicate)
                .mapToInt(Person::getAge)
                .sum();

        //part 3
        int oddAge = people.stream()
                .filter(predicate.and(person -> person.getAge() % 2 == 0))
                .mapToInt(Person::getAge)
                .sum();

        //part 4
        // (A)
        System.out.println(people.stream().mapToInt(Person::getAge).min().orElse(0));
        System.out.println(people.stream().mapToInt(Person::getAge).max().orElse(0));

        //(B)
       // int minAge = people.stream().mapToInt(Person::getAge).reduce(Integer::min).getAsInt();
        int maxAge = people.stream().mapToInt(Person::getAge).reduce(Integer::max).getAsInt();
        int minAge = people.stream()
                .map(Person::getAge)
                .reduce((minimum, age) -> age < minimum ? age : minimum)
                .orElseThrow(() -> new IllegalArgumentException("Exception"));

        //part 5
        List<Person> newPeople =people.stream().filter(predicate).sorted((first , second) -> Integer.compare(first.getAge(), second.getAge())).toList();
        double avg = newPeople.stream().mapToInt(Person::getAge).average().getAsDouble();
        int sum = newPeople.stream().filter(person -> person.getAge() < avg).limit(3).mapToInt(Person::getAge).sum();
    }
}
