package thomashan.github.io.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.Employee
import thomashan.github.io.InMemPayrollDatabase
import thomashan.github.io.PayrollDatabase
import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.method.PaymentMethod
import thomashan.github.io.payroll.schedule.PaymentSchedule
import thomashan.github.io.payroll.schedule.WeeklySchedule
import thomashan.github.io.payroll.transaction.AddHourlyEmployee
import thomashan.github.io.payroll.transaction.Transaction

class AddHourlyEmployeeTests {
    private PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance

    @Test
    void "add hourly employee"() {
        int employeeId = 1
        Transaction transaction = new AddHourlyEmployee(employeeId, "Hourly", "HourlyHome", 100.0)
        transaction.execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.name == "Hourly"
        assert employee.address == "HourlyHome"

        PaymentClassification paymentClassification = employee.paymentClassification
        assert paymentClassification instanceof HourlyClassification

        HourlyClassification hourlyClassification = (HourlyClassification) paymentClassification

        assert hourlyClassification.hourlyRate == 100.0

        PaymentSchedule paymentSchedule = employee.paymentSchedule
        assert paymentSchedule instanceof WeeklySchedule

        PaymentMethod paymentMethod = employee.paymentMethod
        assert paymentMethod instanceof HoldMethod
    }
}
