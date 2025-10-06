package com.stream.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class shows the use of collectingAndThen which is powerful feature
 * use it to unwrap Optionals, convert collection types, or post-process results.
 */

class Employee {
    private String name;
    private String department;
    private int age;

    // constructor + getters
    public Employee(String name, String department, int age) {
        this.name = name;
        this.department = department;
        this.age = age;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("Alice", "HR", 30),
                new Employee("Bob", "HR", 25),
                new Employee("Charlie", "IT", 28),
                new Employee("David", "IT", 24),
                new Employee("Eve", "Finance", 32)
        );

        Map<String, Employee> youngest = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(Employee::getAge)),
                                Optional::get
                        )
                ));
        youngest.forEach((department, employee) ->
                System.out.println(department + " → " + employee)
        );
    }
}

