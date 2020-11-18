package thomashan.github.io.payroll.transaction.add

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.schedule.WeeklySchedule
import thomashan.github.io.payroll.transaction.Command
import thomashan.github.io.payroll.transaction.CommandTests

class AddHourlyEmployeeTests implements CommandTests {
    private Command command
    private String name = "Hourly"
    private String address = "HourlyHome"
    private double hourlyRate = 100.0

    @BeforeEach
    void setUp() {
        command = new AddHourlyEmployee(employeeId, name, address, hourlyRate)
    }

    @Test
    void "add hourly employee should return correct employee name"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).name == name
    }

    @Test
    void "add hourly employee should return correct employee address"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).address == address
    }

    @Test
    void "add hourly employee should return correct payment classification"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentClassification instanceof HourlyClassification
    }

    @Test
    void "add hourly employee should return correct hourlyRate"() {
        command.execute()
        HourlyClassification hourlyClassification = (HourlyClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert hourlyClassification.hourlyRate == hourlyRate
    }

    @Test
    void "add hourly employee should return correct payment schedule"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentSchedule instanceof WeeklySchedule
    }

    @Test
    void "add hourly employee should return correct payment method"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentMethod instanceof HoldMethod
    }
}
