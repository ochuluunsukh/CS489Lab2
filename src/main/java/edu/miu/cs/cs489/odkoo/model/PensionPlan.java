package edu.miu.cs.cs489.odkoo.model;

import java.time.LocalDate;

public class PensionPlan {
    private String planReferenceNumber;
    private LocalDate enrollmentDate;
    private double monthlyContribution;

    public PensionPlan(String planReferenceNumber, LocalDate enrollmentDate, double monthlyContribution) {
        this.planReferenceNumber = planReferenceNumber;
        this.enrollmentDate = enrollmentDate;
        this.monthlyContribution = monthlyContribution;
    }

    // Getters and Setters
    public String getPlanReferenceNumber() { return planReferenceNumber; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public double getMonthlyContribution() { return monthlyContribution; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PensionPlan{");
        sb.append("planReferenceNumber='").append(planReferenceNumber).append('\'');
        sb.append(", enrollmentDate=").append(enrollmentDate);
        sb.append(", monthlyContribution=").append(monthlyContribution);
        sb.append('}');
        return sb.toString();
    }
}
