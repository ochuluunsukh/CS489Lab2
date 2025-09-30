# Employee Pension Plan - CLI

This is a small Java CLI application that prints employee data and a quarterly upcoming enrollees report in JSON format.

Features implemented:

- Print list of all employees in JSON. The list includes pension plan data when present and is sorted by yearly salary (descending) and last name (ascending).
- Print quarterly upcoming enrollees in JSON. This includes employees without a pension plan who will reach 3 years of service during the next quarter. The list is sorted by employment date (descending).
- CI: GitHub Actions workflow to build the project on push and pull requests to `main`.

Build & Run

Requirements: Java 21, Maven

Build:

```bash
mvn -B package
```

Run the jar produced in `target`:

```bash
java -jar target/CS489Lab2-1.0-SNAPSHOT.jar
```
