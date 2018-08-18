package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.affiliation.UnionAffiliation
import thomashan.github.io.payroll.transaction.add.AddHourlyEmployee

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class ServiceChargeTransactionTests implements TransactionTests {
    private int memberId = 1
    private LocalDate today = LocalDate.now()
    private double charge = 10

    @BeforeEach
    void setUp() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 10).execute()
    }

    @Test
    void "add service charge transaction on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new ServiceChargeTransaction(2, today, charge).execute() })
    }

    @Test
    void "add service charge transaction on an existing employee with union affiliation should execute without error"() {
        Employee employee = payrollDatabase.getEmployee(employeeId)
        payrollDatabase.addUnionMember(memberId, employee)
        employee.affiliation = Optional.of(new UnionAffiliation(memberId, 10))
        new ServiceChargeTransaction(employeeId, today, charge).execute()

        assert ((UnionAffiliation) employee.affiliation.get()).getServiceCharge(today).amount == charge
    }
}
