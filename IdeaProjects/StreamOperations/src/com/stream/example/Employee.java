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
    private double salary;

    // constructor + getters
    public Employee(String name, String department, int age,double salary) {
        this.name = name;
        this.department = department;
        this.age = age;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("Alice", "HR", 30,400.89),
                new Employee("Bob", "HR", 25,89970.54),
                new Employee("Charlie", "IT", 28,987.59),
                new Employee("David", "IT", 24,8626.78),
                new Employee("Eve", "Finance", 32,3597.48),
                new Employee("Adam", "Finance", 31,397.48)
        );

        Map<String, Employee> youngest =   groupEmployeeByDepartmentAndGetTheMinAge(employees);
        youngest.forEach((dep,emp)-> System.out.println(dep +" - "+emp));

        Map<String,Double> getEmpDeptAndSalary = groupEmployeeByDeptAndSalary(employees);
        getEmpDeptAndSalary.forEach((dept,sal)-> System.out.println(dept +" - "+sal));

        List<Employee> employeeList = getEmployeeOlderThan30(employees);
        employeeList.forEach(e-> System.out.println(e.name +" "+e.salary));

        Map<String,Long> countDeptAndCount = countEmployeeEachDept(employees);
        countDeptAndCount.forEach((dept,count)-> System.out.println(dept +" "+count));

        Employee secondHighest = findSecondHighest(employees);
        System.out.println(secondHighest.name);
    }

    /**
     *
     * @param employees
     * @return
     */
    private static Map<String, Employee> groupEmployeeByDepartmentAndGetTheMinAge(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(Employee::getAge)),
                                Optional::get
                        )
                ));
    }

    /**
     * @param empList
     * @return
     */
    private static Map<String, Double> groupEmployeeByDeptAndSalary(List<Employee> empList) {
        return empList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.summingDouble(Employee::getSalary)));
    }

    /**
     * Given a list of Person objects, return the names of people older than 30, sorted alphabetically.
     */
    private static List<Employee> getEmployeeOlderThan30(List<Employee> employeeList){
        return employeeList.stream().filter(e->e.getSalary()>30).collect(Collectors.toList());
    }

    /**
     * Count how many employees are in each department
     */
    private static  Map<String,Long> countEmployeeEachDept(List<Employee> employeeList){
        return employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.counting()));
    }

    /**
     * Find the employee with the second-highest salary
     */
    private static Employee findSecondHighest(List<Employee> employees){
      return  employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
              .skip(1)
              .findFirst().orElse(null);
    }








}

