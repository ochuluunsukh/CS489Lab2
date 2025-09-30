package edu.miu.cs.cs489.odkoo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.miu.cs.cs489.odkoo.model.Employee;
import edu.miu.cs.cs489.odkoo.model.PensionPlan;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = loadData();
    // Sort all employees: by yearly salary (desc) then by last name (asc)
    employees.sort(Comparator.comparing(Employee::getYearlySalary, Comparator.reverseOrder())
        .thenComparing(Employee::getLastName));

    // Filter for upcoming enrollees and sort by employment date (desc)
    List<Employee> upcomingEnrollees = employees.stream()
        .filter(Employee::isUpcomingEnrollee)
        .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
        .toList();

    // Print results in JSON format
    printReportAsJson(employees, upcomingEnrollees);
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

    private static Gson buildGson() {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        // LocalDate serializer/deserializer
        builder.registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.format(fmt));
            }
        });
        builder.registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                return LocalDate.parse(json.getAsString(), fmt);
            }
        });
        return builder.create();
    }

    private static void printReportAsJson(List<Employee> allEmployees, List<Employee> upcomingEnrollees) {
        Gson gson = buildGson();

        Map<String, Object> output = new HashMap<>();
        output.put("allEmployees", allEmployees);
        output.put("upcomingEnrollees", upcomingEnrollees);

        // Print all employees JSON
        System.out.println("--- List of All Employees (JSON) ---");
        System.out.println(gson.toJson(allEmployees));

        // Print quarterly upcoming enrollees JSON
        System.out.println("\n--- Quarterly Upcoming Enrollees Report (JSON) ---");
        System.out.println(gson.toJson(upcomingEnrollees));
    }
}