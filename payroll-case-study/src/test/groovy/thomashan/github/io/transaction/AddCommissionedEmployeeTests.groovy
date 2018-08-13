package thomashan.github.io.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase
import thomashan.github.io.payroll.classification.CommissionedClassification
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.method.PaymentMethod
import thomashan.github.io.payroll.schedule.BiweeklySchedule
import thomashan.github.io.payroll.schedule.PaymentSchedule
import thomashan.github.io.payroll.transaction.AddCommissionedEmployee
import thomashan.github.io.payroll.transaction.Transaction

class AddCommissionedEmployeeTests {
    private PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance

    @Test
    void "add commissioned employee"() {
        int employeeId = 1
        Transaction transaction = new AddCommissionedEmployee(employeeId, "Commissioned", "CommissionedHome", 1000.00, 20.0)
        transaction.execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.name == "Commissioned"
        assert employee.address == "CommissionedHome"

        PaymentClassification paymentClassification = employee.paymentClassification
        assert paymentClassification instanceof CommissionedClassification

        CommissionedClassification commissionedClassification = (CommissionedClassification) paymentClassification

        assert commissionedClassification.salary == 1000.00
        assert commissionedClassification.commissionRate == 20.0

        PaymentSchedule paymentSchedule = employee.paymentSchedule
        assert paymentSchedule instanceof BiweeklySchedule

        PaymentMethod paymentMethod = employee.paymentMethod
        assert paymentMethod instanceof HoldMethod
    }
}
