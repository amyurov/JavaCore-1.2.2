import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }

       long underage = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовршеннолетних " + underage);

        List<String> recruits = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18)
                .map(Person::getFamily)
                .limit(20)
                .collect(Collectors.toList());

        System.out.println("Список призывников:\n" + recruits);

        List<String> workingPersonsWithHigherEdu = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() > 18 && person.getAge() <= 65)
                .filter(person -> person.getSex().equals(Sex.WOMAN) && person.getAge() <= 60 || person.getSex().equals(Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily).thenComparing(Person::getName))
                .map(person -> person.getFamily() + " " + person.getName())
                .limit(100)
                .collect(Collectors.toList());

        System.out.println("Список работоспопсобных:\n" + workingPersonsWithHigherEdu);
    }
}
