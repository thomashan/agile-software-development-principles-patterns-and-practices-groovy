package io.github.thomashan.payroll.command.add

import groovy.transform.Canonical
import io.github.thomashan.payroll.classification.HourlyClassification
import io.github.thomashan.payroll.schedule.WeeklySchedule

@Canonical
class AddHourlyEmployee implements AddEmployee {
    final double hourlyRate

    AddHourlyEmployee(int employeeId, String name, String address, double hourlyRate) {
        this.io_github_thomashan_payroll_command_add_AddEmployee__employeeId = employeeId
        this.io_github_thomashan_payroll_command_add_AddEmployee__name = name
        this.io_github_thomashan_payroll_command_add_AddEmployee__address = address
        this.io_github_thomashan_payroll_command_add_AddEmployee__paymentClassification = new HourlyClassification(hourlyRate)
        this.io_github_thomashan_payroll_command_add_AddEmployee__paymentSchedule = WeeklySchedule.instance
        this.hourlyRate = hourlyRate
    }
}