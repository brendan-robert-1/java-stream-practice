package part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {

        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();

        //List all distinct project in non-ascending order.
        System.out.println();
        employeeList.stream()
                .flatMap(e -> e.getProjects().stream()) //flattens the nested project of projects within each employee
                .distinct()
                .sorted(Comparator.comparing(Project::getName, Comparator.reverseOrder()))
                .forEachOrdered(s -> System.out.println(s.getName()));

        //Print full name of any employee whose firstName starts with ‘A’.
        System.out.println();
        employeeList.stream()
                .filter(e -> e.getFirstName().startsWith("A"))
                .forEach(s -> System.out.println(s.getFirstName()));

        //List of all employee who joined in year 2023 (year to be extracted from employee id i.e., 1st 4 characters)
        System.out.println();
        System.out.println("List of all employee who joined in year 2023 (year to be extracted from employee id i.e., 1st 4 characters)");
        employeeList.stream()
                .filter(e -> e.getId().startsWith("2023"))
                .forEach(s -> System.out.println(s.getFirstName() + " " + s.getId()));

        //Sort employees based on firstName, for same firstName sort by salary.
        System.out.println("Sort employees based on firstName, for same firstName sort by salary.");
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .sorted(Comparator.comparing(Employee::getFirstName, Comparator.naturalOrder())) //Alternatively use thenComparing
                .forEachOrdered(s -> System.out.println(s.getFirstName() + " " + s.getSalary()));

        //Print names of all employee with 3rd highest salary. (generalise it for nth highest salary).
        System.out.println();
        System.out.println("Print names of all employee with 3rd highest salary. (generalise it for nth highest salary).");
        int nthHighest = 3;
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder()))
                .limit(nthHighest) // returns stream only nthHighest long
                .skip(nthHighest - 1) //skips to the last one
                .findFirst() //gets the last one
                .ifPresent(nthHighestEmployee ->
                        employeeList.stream()
                                .filter(e -> e.getSalary() == nthHighestEmployee.getSalary())
                                .forEach(s -> System.out.println(s.getFirstName() + " " + s.getSalary()))
                );

        //Print min salary.
        System.out.println();
        System.out.println("Print min salary.");
        employeeList.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .ifPresent(e -> System.out.println("min salary: " + e.getSalary()));

        //Print list of all employee with min salary.
        System.out.println();
        System.out.println("Print list of all employee with min salary.");
        employeeList.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .ifPresent(lowestSalaryEmployee ->
                        employeeList.stream()
                                .filter(e -> e.getSalary() == lowestSalaryEmployee.getSalary())
                                .forEach(s -> System.out.println(s.getFirstName() + " " + s.getSalary()))
                );

        //List of people working on more than 2 projects.
        System.out.println();
        System.out.println("List of people working on more than 2 projects.");
        employeeList.stream()
                .filter(e -> e.getProjects().size() > 2)
                .forEach(s -> System.out.println(s.getFirstName() + " Total projects: " + s.getProjects().size()));

        //Count of total laptops assigned to the employees.
        System.out.println();
        System.out.println("Total laptops assigned to all employees: " + employeeList.stream()
                        .mapToInt(Employee::getTotalLaptopsAssigned)
                        .sum());

        // Count of all projects with Robert Downey Jr as PM.
        System.out.println();
        System.out.println("Count of all projects with Robert Downey Jr as Project Manager: " +
                employeeList.stream()
                        .flatMap(e -> e.getProjects().stream())
                        .filter(p -> p.getProjectManager().equals("Robert Downey Jr"))
                        .distinct()
                        .count()
        );

        //List of all projects with Robert Downey Jr as PM.
        System.out.println();
        employeeList.stream()
                .flatMap(e -> e.getProjects().stream())
                .filter(p -> p.getProjectManager().equals("Robert Downey Jr"))
                .distinct()
                .forEach(s -> System.out.println(s.getName() + " " + s.getProjectManager()));

        // List of all people working with Robert Downey Jr.
        System.out.println();
        System.out.println("Employees working with Robert Downey Jr as a PM: ");
        employeeList.stream()
                .filter(e -> e.getProjects().stream()
                        .anyMatch(p -> p.getProjectManager().equals("Robert Downey Jr"))
                )
                .forEach(s -> System.out.println(s.getFirstName()));

        //Create a map based on this data, they key should be the year of joining, and value should be list of all the employees who joined the particular year.
        System.out.println();
        System.out.println("Create a map based on this data, they key should be the year of joining, and value should be list of all the employees who joined the particular year.");
        Map<Integer, List< Employee>> employeeYearMap = employeeList.stream()
                .collect(Collectors.groupingBy(e -> Integer.valueOf(e.getId().substring(0,4))));
        employeeYearMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> {
                    System.out.println("Year: " + entry.getKey());
                    entry.getValue().forEach(e -> System.out.println(convertToString(e)));
                });

        // Create a map based on this data, the key should be year of joining and value should be the count of people joined in that particular year.
        System.out.println();
        System.out.println(" Create a map based on this data, the key should be year of joining and value should be the count of people joined in that particular year.");
        var yearToPeopleMap =
                employeeList.stream()
                        .collect(
                                Collectors.groupingBy(e -> Integer.valueOf(e.getId().substring(0,4)), Collectors.counting())
                        );
        yearToPeopleMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(entry -> System.out.println("Year: " + entry.getKey() + " Count of people that joined this year: " + entry.getValue()));
    }

    private static String convertToString(Employee employee) {
        return String.format("id: %-12s sal:%8d fn:%-8s ln:%-10s", employee.getId(), employee.getSalary(), employee.getFirstName(), employee.getLastName());
    }
}