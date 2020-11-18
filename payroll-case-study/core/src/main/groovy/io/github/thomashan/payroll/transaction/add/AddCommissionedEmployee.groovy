package io.github.thomashan.payroll.transaction.add

import groovy.transform.Canonical
import io.github.thomashan.payroll.classification.CommissionedClassification

@Canonical
class AddCommissionedEmployee implements AddEmployeeCommand {
    final double monthlySalary
    final double commissionRate

    AddCommissionedEmployee(int employeeId, String name, String address, double monthlySalary, double commissionRate) {
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__employeeId = employeeId
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__name = name
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__address = address
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__paymentClassification = new CommissionedClassification(monthlySalary, commissionRate)
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__paymentSchedule = io.github.thomashan.payroll.schedule.BiweeklySchedule.instance
        this.monthlySalary = monthlySalary
        this.commissionRate = commissionRate
    }
}
