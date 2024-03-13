package ru.gb.lesson1.hw;

import ru.gb.lesson1.Streams;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingDouble;

public class Homework {
  public static void main(String[] args) {
    List<Streams.Person> persons = new ArrayList<>();
    persons.add(new Streams.Person("Alice", 25, 60000, new Streams.Department("HR")));
    persons.add(new Streams.Person("Bob", 30, 400000,new Streams.Department("Super")));
    persons.add(new Streams.Person("Charlie", 35, 500000,new Streams.Department("Super")));
    persons.add(new Streams.Person("Ben", 26, 300345240,new Streams.Department("Super")));


    findTopDepartment(persons);
  }


  /**
   * Вывести на консоль отсортированные (по алфавиту) имена персонов
   */
  public static void printNamesOrdered(List<Streams.Person> persons) {
    persons.stream()
            .sorted(Comparator.comparing(Streams.Person::getName));

    for (Streams.Person person : persons) {
      System.out.println(person);
    }

  }

  /**
   * В каждом департаменте найти самого взрослого сотрудника.
   * Вывести на консоль мапипнг department -> personName
   * Map<Department, Person>
   */
  public static Map<Streams.Department, Streams.Person> printDepartmentOldestPerson(List<Streams.Person> persons) {

    Comparator<Streams.Person> ageComparator = Comparator.comparing(Streams.Person::getAge);
    Map<Streams.Department, Streams.Person> maxAge = persons.stream()
            .collect(Collectors.toMap(Streams.Person::getDepartment, Function.identity(), (first, second) -> {
              if (ageComparator.compare(first, second) > 0) {
                return first;
              }

              return second;
            }));
      return maxAge;
  }

  /**
   * Найти 10 первых сотрудников, младше 30 лет, у которых зарплата выше 50_000
   */
  public static void findFirstPersons(List<Streams.Person> persons) {
    List<Streams.Person> filteredEmployees = persons.stream()
            .filter(per -> per.getAge() < 30 && per.getSalary() > 50000)
            .limit(10)
            .collect(Collectors.toList());

    filteredEmployees.forEach(System.out::println);

  }

  /**
   * Найти депаратмент, чья суммарная зарплата всех сотрудников максимальна
   */

  public static void findTopDepartment(List<Streams.Person> persons) {
    Optional<Map.Entry<Streams.Department, Double>> max = persons.stream()
            .collect(Collectors.groupingBy(Streams.Person::getDepartment, Collectors.summingDouble(Streams.Person::getSalary)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue());
    System.out.println(max);
  }}





