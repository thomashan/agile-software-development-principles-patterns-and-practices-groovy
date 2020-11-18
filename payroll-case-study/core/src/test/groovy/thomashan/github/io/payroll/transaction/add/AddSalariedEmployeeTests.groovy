package thomashan.github.io.payroll.transaction.add

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.classification.SalariedClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.schedule.MonthlySchedule
import thomashan.github.io.payroll.transaction.Command
import thomashan.github.io.payroll.transaction.CommandTests

class AddSalariedEmployeeTests implements CommandTests {
    private Command command
    private String name = "Salaried"
    private String address = "SalariedHome"
    private double salary = 1000.0

    @BeforeEach
    void setUp() {
        command = new AddSalariedEmployee(employeeId, name, address, salary)
    }

    @Test
    void "add salaried employee should return correct employee name"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).name == name
    }

    @Test
    void "add salaried employee should return correct employee address"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).address == address
    }

    @Test
    void "add salaried employee should return correct payment classification"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentClassification instanceof SalariedClassification
    }

    @Test
    void "add salaried employee should return correct salary"() {
        command.execute()
        SalariedClassification salariedClassification = (SalariedClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert salariedClassification.salary == salary
    }

    @Test
    void "add salaried employee should return correct payment schedule"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentSchedule instanceof MonthlySchedule
    }

    @Test
    void "add salaried employee should return correct payment method"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentMethod instanceof HoldMethod
    }
}
