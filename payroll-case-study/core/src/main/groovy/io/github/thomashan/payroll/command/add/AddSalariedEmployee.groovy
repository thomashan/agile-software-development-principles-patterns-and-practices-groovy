package io.github.thomashan.payroll.command.add

import groovy.transform.Canonical
import io.github.thomashan.payroll.classification.SalariedClassification
import io.github.thomashan.payroll.schedule.MonthlySchedule

@Canonical
class AddSalariedEmployee implements AddEmployee {
    final double monthlySalary

    AddSalariedEmployee(int employeeId, String name, String address, double monthlySalary) {
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__employeeId = employeeId
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__name = name
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__address = address
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__paymentClassification = new SalariedClassification(monthlySalary)
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__paymentSchedule = MonthlySchedule.instance
        this.monthlySalary = monthlySalary
    }
}
