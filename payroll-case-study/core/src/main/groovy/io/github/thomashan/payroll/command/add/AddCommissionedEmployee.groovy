package io.github.thomashan.payroll.command.add

import groovy.transform.Canonical
import io.github.thomashan.payroll.classification.CommissionedClassification
import io.github.thomashan.payroll.schedule.BiweeklySchedule

@Canonical
class AddCommissionedEmployee implements AddEmployee {
    final double monthlySalary
    final double commissionRate

    AddCommissionedEmployee(int employeeId, String name, String address, double monthlySalary, double commissionRate) {
        this.io_github_thomashan_payroll_command_add_AddEmployee__employeeId = employeeId
        this.io_github_thomashan_payroll_command_add_AddEmployee__name = name
        this.io_github_thomashan_payroll_command_add_AddEmployee__address = address
        this.io_github_thomashan_payroll_command_add_AddEmployee__paymentClassification = new CommissionedClassification(monthlySalary, commissionRate)
        this.io_github_thomashan_payroll_command_add_AddEmployee__paymentSchedule = BiweeklySchedule.instance
        this.monthlySalary = monthlySalary
        this.commissionRate = commissionRate
    }
}
