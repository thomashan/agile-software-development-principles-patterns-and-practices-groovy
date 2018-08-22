package thomashan.github.io.payroll.transaction.update

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.PayCheque
import thomashan.github.io.payroll.affiliation.Affiliation
import thomashan.github.io.payroll.transaction.add.AddHourlyEmployee

import static org.junit.jupiter.api.Assertions.assertThrows

class ChangeUnaffiliatedTransactionTests implements ChangeEmployeeTransactionTests {
    private int memberId = 1

    @BeforeEach
    void setUp() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
    }

    @Test
    void "change unaffiliated transaction should execute without error"() {
        double dues = 10
        new ChangeMemberTransaction(employeeId, memberId, dues).execute()

        new ChangeUnaffiliatedTransaction(employeeId).execute()

        assert payrollDatabase.getUnionMember(memberId) == null
    }

    @Test
    void "change unaffiliated transaction should show an error if the employee has no affiliation"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()

        assertThrows(RuntimeException, { new ChangeUnaffiliatedTransaction(employeeId).execute() })
    }

    @Test
    void "change unaffiliated transaction should show an error if the employee is not affiliated with union"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)
        employee.affiliation = Optional.of(new NoAffiliation())

        assertThrows(RuntimeException, { new ChangeUnaffiliatedTransaction(employeeId).execute() })
    }

    private static class NoAffiliation implements Affiliation {
        @Override
        double calculateDeductions(PayCheque payCheque) {
            return 0
        }
    }
}
