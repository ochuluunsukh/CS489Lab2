package edu.miu.cs.cs489.odkoo;
import edu.miu.cs.cs489.odkoo.model.Employee;
import edu.miu.cs.cs489.odkoo.model.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = loadData();

        // Sort all employees: by last name (asc), then by yearly salary (desc)
        employees.sort(Comparator.comparing(Employee::getLastName)
                .thenComparing(Employee::getYearlySalary, Comparator.reverseOrder()));

        // Filter for upcoming enrollees and sort by employment date (asc)
        List<Employee> upcomingEnrollees = employees.stream()
                .filter(Employee::isUpcomingEnrollee)
                .sorted(Comparator.comparing(Employee::getEmploymentDate))
                .toList();

        // Print results in JSON format
        printReport(employees, upcomingEnrollees);
    }

    private static List<Employee> loadData() {
        List<Employee> employees = new ArrayList<>();

        Employee e1 = new Employee(1, "Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.50);
        PensionPlan p1 = new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100.00);
        e1.setPensionPlan(p1);
        employees.add(e1);

        Employee e2 = new Employee(2, "Bernard", "Shaw", LocalDate.of(2019, 4, 3), 197750.00);
        employees.add(e2);

        Employee e3 = new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75);
        PensionPlan p3 = new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.50);
        e3.setPensionPlan(p3);
        employees.add(e3);

        Employee e4 = new Employee(4, "Wesley", "Schneider", LocalDate.of(2019, 5, 2), 74500.00);
        employees.add(e4);

        // Upcoming enrollee based on current date (Sep 2025 -> next qtr is Q4 2025)
        // 3-year anniversary is 2025-11-15, which is in Q4 2025
        Employee e5 = new Employee(5, "Jennifer", "Smith", LocalDate.of(2022, 11, 15), 120000.00);
        employees.add(e5);


        return employees;
    }

    private static void printReport(List<Employee> allEmployees, List<Employee> upcomingEnrollees) {
        System.out.println("--- List of All Employees ---");
        allEmployees.stream()
                .forEach(System.out::println);
        System.out.println("\n--- Quarterly Upcoming Enrollees Report ---");
        upcomingEnrollees.stream()
                .forEach(System.out::println);
    }
}