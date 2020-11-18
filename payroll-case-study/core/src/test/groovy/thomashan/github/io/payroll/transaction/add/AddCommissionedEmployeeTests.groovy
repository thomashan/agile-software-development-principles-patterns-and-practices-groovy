package thomashan.github.io.payroll.transaction.add

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.classification.CommissionedClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.schedule.BiweeklySchedule
import thomashan.github.io.payroll.transaction.Command
import thomashan.github.io.payroll.transaction.CommandTests

class AddCommissionedEmployeeTests implements CommandTests {
    private Command command
    private String name = "Commissioned"
    private String address = "CommissionedHome"
    private double salary = 1000.0
    private double commissionRate = 20.0

    @BeforeEach
    void setUp() {
        command = new AddCommissionedEmployee(employeeId, name, address, salary, commissionRate)
    }

    @Test
    void "add commissioned employee should return correct employee name"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).name == name
    }

    @Test
    void "add commissioned employee should return correct employee address"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).address == address
    }

    @Test
    void "add commissioned employee should return correct payment classification"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentClassification instanceof CommissionedClassification
    }

    @Test
    void "add commissioned employee should return correct salary"() {
        command.execute()
        CommissionedClassification commissionedClassification = (CommissionedClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert commissionedClassification.salary == salary
    }

    @Test
    void "add commissioned employee should return correct commission rate"() {
        command.execute()
        CommissionedClassification commissionedClassification = (CommissionedClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert commissionedClassification.commissionRate == commissionRate
    }

    @Test
    void "add commissioned employee should return correct payment schedule"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentSchedule instanceof BiweeklySchedule
    }

    @Test
    void "add commissioned employee should return correct payment method"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentMethod instanceof HoldMethod
    }
}
