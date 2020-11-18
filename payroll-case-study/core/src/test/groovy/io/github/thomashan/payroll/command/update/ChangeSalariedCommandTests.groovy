package io.github.thomashan.payroll.command.update


import org.junit.jupiter.api.Test

class ChangeSalariedCommandTests implements ChangeEmployeeCommandTests {
    private double newSalary = 2000

    @Test
    void "non-salaried employee should be able to change salaried employee"() {
        new io.github.thomashan.payroll.command.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeSalariedCommand(employeeId, newSalary).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        io.github.thomashan.payroll.classification.SalariedClassification salariedClassification = employee.paymentClassification

        assert salariedClassification.salary == newSalary
        assert employee.paymentSchedule == io.github.thomashan.payroll.schedule.MonthlySchedule.instance
    }

    @Test
    void "salaried employee should be able to change salaried employee with updated salary"() {
        new io.github.thomashan.payroll.command.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeSalariedCommand(employeeId, newSalary).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        io.github.thomashan.payroll.classification.SalariedClassification salariedClassification = employee.paymentClassification

        assert salariedClassification.salary == newSalary
        assert employee.paymentSchedule == io.github.thomashan.payroll.schedule.MonthlySchedule.instance
    }
}
