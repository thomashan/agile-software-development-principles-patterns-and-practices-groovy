package io.github.thomashan.payroll.event.add

import io.github.thomashan.payroll.classification.SalariedClassification
import io.github.thomashan.payroll.schedule.MonthlySchedule

class SalariedEmployeeCreated implements EmployeeCreated {
    final double monthlySalary

    SalariedEmployeeCreated(int employeeId, String name, String address, double monthlySalary) {
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__employeeId = employeeId
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__name = name
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__address = address
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__paymentClassification = new SalariedClassification(monthlySalary)
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__paymentSchedule = MonthlySchedule.instance
        this.monthlySalary = monthlySalary
    }
}
