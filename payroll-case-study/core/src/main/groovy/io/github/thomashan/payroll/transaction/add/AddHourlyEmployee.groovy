package io.github.thomashan.payroll.transaction.add

import groovy.transform.Canonical
import io.github.thomashan.payroll.classification.HourlyClassification

@Canonical
class AddHourlyEmployee implements AddEmployeeCommand {
    final double hourlyRate

    AddHourlyEmployee(int employeeId, String name, String address, double hourlyRate) {
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__employeeId = employeeId
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__name = name
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__address = address
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__paymentClassification = new HourlyClassification(hourlyRate)
        this.io_github_thomashan_payroll_transaction_add_AddEmployeeCommand__paymentSchedule = io.github.thomashan.payroll.schedule.WeeklySchedule.instance
        this.hourlyRate = hourlyRate
    }
}
