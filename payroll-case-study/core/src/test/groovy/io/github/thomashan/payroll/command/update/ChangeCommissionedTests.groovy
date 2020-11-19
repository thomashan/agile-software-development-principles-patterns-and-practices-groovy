package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.classification.CommissionedClassification
import io.github.thomashan.payroll.command.add.AddCommissionedEmployee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import io.github.thomashan.payroll.schedule.BiweeklySchedule
import org.junit.jupiter.api.Test

class ChangeCommissionedTests implements ChangeEmployeeTests {
    private double newSalary = 2000
    double newCommissionRate = 10

    @Test
    void "non-commissioned employee should be able to change commissioned employee"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeCommissioned(employeeId, newSalary, newCommissionRate).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        CommissionedClassification commissionedClassification = employee.paymentClassification

        assert commissionedClassification.salary == newSalary
        assert commissionedClassification.commissionRate == newCommissionRate
        assert employee.paymentSchedule == BiweeklySchedule.instance
    }

    @Test
    void "commissioned employee should be able to change commissioned employee with updated salary and commission rate"() {
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 100, 100).execute()
        new ChangeCommissioned(employeeId, newSalary, newCommissionRate).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        CommissionedClassification commissionedClassification = employee.paymentClassification

        assert commissionedClassification.salary == newSalary
        assert commissionedClassification.commissionRate == newCommissionRate
        assert employee.paymentSchedule == BiweeklySchedule.instance
    }
}
