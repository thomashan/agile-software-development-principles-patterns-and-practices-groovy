package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.classification.HourlyClassification
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import io.github.thomashan.payroll.schedule.WeeklySchedule
import org.junit.jupiter.api.Test

class ChangeHourlyTests implements ChangeEmployeeTests {
    private double newHourlyRate = 100

    @Test
    void "non-hourly employee should be able to change hourly employee"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeHourly(employeeId, newHourlyRate).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert ((HourlyClassification) employee.paymentClassification).hourlyRate == newHourlyRate
        assert employee.paymentSchedule == WeeklySchedule.instance
    }

    @Test
    void "hourly employee should be able to change hourly employee with updated hourly rate"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeHourly(employeeId, newHourlyRate).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert ((HourlyClassification) employee.paymentClassification).hourlyRate == newHourlyRate
        assert employee.paymentSchedule == WeeklySchedule.instance
    }
}
