import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main1 {

    public static class Person {
        private final String name;
        private final int salary;
        private final String department;

        public Person(String name, int salary, String department) {
            this.name = name;
            this.salary = salary;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public int getSalary() {
            return salary;
        }

        public String getDepartment() {
            return department;
        }
    }

    public static class Department {
        private final String name;
        private final int totalSalary;

        public Department(String name, int totalSalary) {
            this.name = name;
            this.totalSalary = totalSalary;
        }

        public String getName() {
            return name;
        }

        public int getTotalSalary() {
            return totalSalary;
        }
    }

    /**
     * Найти депаратмент, чья суммарная зарплата всех сотрудников максимальна
     */
    public Optional<Department> findTopDepartment(List<Person> persons) {
        return persons.stream()
                .collect(Collectors.groupingBy(Person::getDepartment, Collectors.summingInt(Person::getSalary)))
                .entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() - entry2.getValue())
                .map(entry -> new Department(entry.getKey(), entry.getValue()));
    }
}
