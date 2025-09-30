package edu.miu.cs.cs489.odkoo.model;

import java.time.LocalDate;

public class Employee {
    private long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private double yearlySalary;
    private PensionPlan pensionPlan;

    public Employee(long employeeId, String firstName, String lastName, LocalDate employmentDate, double yearlySalary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
    }

    // Getters and Setters
    public long getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDate getEmploymentDate() { return employmentDate; }
    public double getYearlySalary() { return yearlySalary; }
    public PensionPlan getPensionPlan() { return pensionPlan; }
    public void setPensionPlan(PensionPlan pensionPlan) { this.pensionPlan = pensionPlan; }

    public boolean isUpcomingEnrollee() {
        if (this.pensionPlan != null) {
            return false;
        }
        LocalDate now = LocalDate.now();
        LocalDate threeYearsAfterEmployment = this.employmentDate.plusYears(3);

        // Determine next quarter
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        LocalDate nextQuarterStart;
        if (currentMonth <= 3) { // Q1
            nextQuarterStart = LocalDate.of(currentYear, 4, 1);
        } else if (currentMonth <= 6) { // Q2
            nextQuarterStart = LocalDate.of(currentYear, 7, 1);
        } else if (currentMonth <= 9) { // Q3
            nextQuarterStart = LocalDate.of(currentYear, 10, 1);
        } else { // Q4
            nextQuarterStart = LocalDate.of(currentYear + 1, 1, 1);
        }
        LocalDate nextQuarterEnd = nextQuarterStart.plusMonths(3).minusDays(1);

        return !threeYearsAfterEmployment.isBefore(nextQuarterStart) && !threeYearsAfterEmployment.isAfter(nextQuarterEnd);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("employeeId=").append(employeeId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", employmentDate=").append(employmentDate);
        sb.append(", yearlySalary=").append(yearlySalary);
        sb.append(", pensionPlan=").append(pensionPlan);
        sb.append('}');
        return sb.toString();
    }
}
