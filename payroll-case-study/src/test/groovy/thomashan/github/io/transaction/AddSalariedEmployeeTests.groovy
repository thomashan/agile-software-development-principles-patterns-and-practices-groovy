package thomashan.github.io.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.classification.SalariedClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.method.PaymentMethod
import thomashan.github.io.payroll.schedule.MonthlySchedule
import thomashan.github.io.payroll.schedule.PaymentSchedule
import thomashan.github.io.payroll.transaction.AddSalariedEmployee
import thomashan.github.io.payroll.transaction.Transaction

class AddSalariedEmployeeTests {
    private PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance

    @Test
    void "add salaried employee"() {
        int employeeId = 1
        Transaction transaction = new AddSalariedEmployee(employeeId, "Bob", "Home", 1000.00)
        transaction.execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.name == "Bob"
        assert employee.address == "Home"

        PaymentClassification paymentClassification = employee.paymentClassification
        assert paymentClassification instanceof SalariedClassification

        SalariedClassification salariedClassification = (SalariedClassification) paymentClassification

        assert salariedClassification.salary == 1000.00

        PaymentSchedule paymentSchedule = employee.paymentSchedule
        assert paymentSchedule instanceof MonthlySchedule

        PaymentMethod paymentMethod = employee.paymentMethod
        assert paymentMethod instanceof HoldMethod
    }
}
