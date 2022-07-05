import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> underage = persons.stream();
        long count = underage
                .filter(x -> x.getAge() < 18)
                .count();

        Stream<Person> military = persons.stream();
        List<String> familyMilitary = military
                .filter(x -> x.getAge() <= 27 && x.getAge() >= 18)
                .filter(x -> x.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());

        Stream<Person> worker = persons.stream();
        List<Person> resultWorker = worker
                .filter(x -> x.getAge() >= 18 && x.getAge() <= (x.getSex().equals("MAN") ? 65 : 60))
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
