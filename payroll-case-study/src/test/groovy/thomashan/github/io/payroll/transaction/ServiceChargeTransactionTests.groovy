package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase
import thomashan.github.io.payroll.affiliation.UnionAffiliation

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class ServiceChargeTransactionTests {
    private PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    private int employeeId = 1
    private int memberId = 1
    private LocalDate today = LocalDate.now()
    private double charge = 10

    @BeforeEach
    void setUp() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 10).execute()
    }

    @AfterEach
    void tearDown() {
        Employee employee = payrollDatabase.getEmployee(employeeId)
        if (employee) {
            payrollDatabase.deleteEmployee(employeeId)
        }
    }

    @Test
    void "add service charge transaction on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new ServiceChargeTransaction(2, today, charge).execute() })
    }

    @Test
    void "add service charge transaction on an existing employee with union affiliation should execute without error"() {
        Employee employee = payrollDatabase.getEmployee(employeeId)
        employee.affiliation = Optional.of(new UnionAffiliation())
        payrollDatabase.addUnionMember(memberId, employee)
        new ServiceChargeTransaction(employeeId, today, charge).execute()

        assert ((UnionAffiliation) employee.affiliation.get()).getServiceCharge(today).amount == charge
    }
}
