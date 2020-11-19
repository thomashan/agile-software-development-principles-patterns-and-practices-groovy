package io.github.thomashan.payroll.command.add

import groovy.transform.Canonical
import io.github.thomashan.payroll.classification.HourlyClassification
import io.github.thomashan.payroll.schedule.WeeklySchedule

@Canonical
class AddHourlyEmployee implements AddEmployeeCommand {
    final double hourlyRate

    AddHourlyEmployee(int employeeId, String name, String address, double hourlyRate) {
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__employeeId = employeeId
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__name = name
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__address = address
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__paymentClassification = new HourlyClassification(hourlyRate)
        this.io_github_thomashan_payroll_command_add_AddEmployeeCommand__paymentSchedule = WeeklySchedule.instance
        this.hourlyRate = hourlyRate
    }
}
