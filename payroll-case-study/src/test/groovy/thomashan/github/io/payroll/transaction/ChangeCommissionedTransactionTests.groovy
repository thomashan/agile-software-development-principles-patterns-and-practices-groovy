package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.classification.CommissionedClassification
import thomashan.github.io.payroll.schedule.BiweeklySchedule

class ChangeCommissionedTransactionTests implements TransactionTests {
    private double newSalary = 2000
    double newCommissionRate = 10

    @Test
    void "non-commissioned employee should be able to change commissioned employee"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeCommissionedTransaction(employeeId, newSalary, newCommissionRate).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        CommissionedClassification commissionedClassification = employee.paymentClassification

        assert commissionedClassification.salary == newSalary
        assert commissionedClassification.commissionRate == newCommissionRate
        assert employee.paymentSchedule == new BiweeklySchedule()
    }

    @Test
    void "commissioned employee should be able to change commissioned employee with updated salary and commission rate"() {
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 100, 100).execute()
        new ChangeCommissionedTransaction(employeeId, newSalary, newCommissionRate).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        CommissionedClassification commissionedClassification = employee.paymentClassification

        assert commissionedClassification.salary == newSalary
        assert commissionedClassification.commissionRate == newCommissionRate
        assert employee.paymentSchedule == new BiweeklySchedule()
    }
}
