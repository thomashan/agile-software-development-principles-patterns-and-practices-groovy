package thomashan.github.io.transaction

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase
import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.schedule.WeeklySchedule
import thomashan.github.io.payroll.transaction.AddHourlyEmployee
import thomashan.github.io.payroll.transaction.Transaction

class AddHourlyEmployeeTests {
    private PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    private Transaction transaction
    private int employeeId = 1
    private String name = "Hourly"
    private String address = "HourlyHome"
    private double hourlyRate = 100.0

    @BeforeEach
    void setUp() {
        transaction = new AddHourlyEmployee(employeeId, name, address, hourlyRate)
    }

    @Test
    void "add hourly employee should return correct employee name"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).name == name
    }

    @Test
    void "add hourly employee should return correct employee address"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).address == address
    }

    @Test
    void "add hourly employee should return correct payment classification"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentClassification instanceof HourlyClassification
    }

    @Test
    void "add hourly employee should return correct hourlyRate"() {
        transaction.execute()
        HourlyClassification hourlyClassification = (HourlyClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert hourlyClassification.hourlyRate == hourlyRate
    }

    @Test
    void "add hourly employee should return correct payment schedule"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentSchedule instanceof WeeklySchedule
    }

    @Test
    void "add hourly employee should return correct payment method"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentMethod instanceof HoldMethod
    }
}
