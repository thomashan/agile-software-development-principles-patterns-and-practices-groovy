package io.github.thomashan.payroll.transaction.update


import org.junit.jupiter.api.Test

class ChangeCommissionedCommandTests implements ChangeEmployeeCommandTests {
    private double newSalary = 2000
    double newCommissionRate = 10

    @Test
    void "non-commissioned employee should be able to change commissioned employee"() {
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeCommissionedCommand(employeeId, newSalary, newCommissionRate).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        io.github.thomashan.payroll.classification.CommissionedClassification commissionedClassification = employee.paymentClassification

        assert commissionedClassification.salary == newSalary
        assert commissionedClassification.commissionRate == newCommissionRate
        assert employee.paymentSchedule == io.github.thomashan.payroll.schedule.BiweeklySchedule.instance
    }

    @Test
    void "commissioned employee should be able to change commissioned employee with updated salary and commission rate"() {
        new io.github.thomashan.payroll.transaction.add.AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 100, 100).execute()
        new ChangeCommissionedCommand(employeeId, newSalary, newCommissionRate).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        io.github.thomashan.payroll.classification.CommissionedClassification commissionedClassification = employee.paymentClassification

        assert commissionedClassification.salary == newSalary
        assert commissionedClassification.commissionRate == newCommissionRate
        assert employee.paymentSchedule == io.github.thomashan.payroll.schedule.BiweeklySchedule.instance
    }
}
