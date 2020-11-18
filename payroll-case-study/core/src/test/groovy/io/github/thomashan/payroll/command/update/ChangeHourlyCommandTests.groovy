package io.github.thomashan.payroll.command.update


import org.junit.jupiter.api.Test

class ChangeHourlyCommandTests implements ChangeEmployeeCommandTests {
    private double newHourlyRate = 100

    @Test
    void "non-hourly employee should be able to change hourly employee"() {
        new io.github.thomashan.payroll.command.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeHourlyCommand(employeeId, newHourlyRate).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)

        assert ((io.github.thomashan.payroll.classification.HourlyClassification) employee.paymentClassification).hourlyRate == newHourlyRate
        assert employee.paymentSchedule == io.github.thomashan.payroll.schedule.WeeklySchedule.instance
    }

    @Test
    void "hourly employee should be able to change hourly employee with updated hourly rate"() {
        new io.github.thomashan.payroll.command.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeHourlyCommand(employeeId, newHourlyRate).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)

        assert ((io.github.thomashan.payroll.classification.HourlyClassification) employee.paymentClassification).hourlyRate == newHourlyRate
        assert employee.paymentSchedule == io.github.thomashan.payroll.schedule.WeeklySchedule.instance
    }
}
