package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.classification.SalariedClassification
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import io.github.thomashan.payroll.schedule.MonthlySchedule
import org.junit.jupiter.api.Test

class ChangeSalariedTests implements ChangeEmployeeTests {
    private double newSalary = 2000

    @Test
    void "non-salaried employee should be able to change salaried employee"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeSalaried(employeeId, newSalary).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        SalariedClassification salariedClassification = employee.paymentClassification

        assert salariedClassification.salary == newSalary
        assert employee.paymentSchedule == MonthlySchedule.instance
    }

    @Test
    void "salaried employee should be able to change salaried employee with updated salary"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeSalaried(employeeId, newSalary).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        SalariedClassification salariedClassification = employee.paymentClassification

        assert salariedClassification.salary == newSalary
        assert employee.paymentSchedule == MonthlySchedule.instance
    }
}
