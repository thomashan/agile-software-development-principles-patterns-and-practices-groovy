package io.github.thomashan.payroll.event.add

import io.github.thomashan.payroll.classification.HourlyClassification
import io.github.thomashan.payroll.schedule.WeeklySchedule

class HourlyEmployeeCreated implements EmployeeCreated {
    final double hourlyRate

    HourlyEmployeeCreated(int employeeId, String name, String address, double hourlyRate) {
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__employeeId = employeeId
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__name = name
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__address = address
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__paymentClassification = new HourlyClassification(hourlyRate)
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__paymentSchedule = WeeklySchedule.instance
        this.hourlyRate = hourlyRate
    }
}
