package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.classification.SalariedClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.schedule.MonthlySchedule

class AddSalariedEmployeeTests implements TransactionTests {
    private Transaction transaction
    private String name = "Salaried"
    private String address = "SalariedHome"
    private double salary = 1000.0

    @BeforeEach
    void setUp() {
        transaction = new AddSalariedEmployee(employeeId, name, address, salary)
    }

    @Test
    void "add salaried employee should return correct employee name"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).name == name
    }

    @Test
    void "add salaried employee should return correct employee address"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).address == address
    }

    @Test
    void "add salaried employee should return correct payment classification"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentClassification instanceof SalariedClassification
    }

    @Test
    void "add salaried employee should return correct salary"() {
        transaction.execute()
        SalariedClassification salariedClassification = (SalariedClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert salariedClassification.salary == salary
    }

    @Test
    void "add salaried employee should return correct payment schedule"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentSchedule instanceof MonthlySchedule
    }

    @Test
    void "add salaried employee should return correct payment method"() {
        transaction.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentMethod instanceof HoldMethod
    }
}
