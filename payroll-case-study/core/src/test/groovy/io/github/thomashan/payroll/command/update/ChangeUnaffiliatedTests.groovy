package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.PayCheque
import io.github.thomashan.payroll.affiliation.Affiliation
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class ChangeUnaffiliatedTests implements ChangeEmployeeTests {
    private int memberId = 1

    @BeforeEach
    void setUp() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
    }

    @Test
    void "change unaffiliated command should execute without error"() {
        double dues = 10
        new ChangeMember(employeeId, memberId, dues).execute()

        new ChangeUnaffiliated(employeeId).execute()

        assert payrollDatabase.getUnionMember(memberId) == null
    }

    @Test
    void "change unaffiliated command should show an error if the employee has no affiliation"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()

        assertThrows(RuntimeException, { new ChangeUnaffiliated(employeeId).execute() })
    }

    @Test
    void "change unaffiliated command should show an error if the employee is not affiliated with union"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)
        employee.affiliation = Optional.of(new NoAffiliation())

        assertThrows(RuntimeException, { new ChangeUnaffiliated(employeeId).execute() })
    }

    private static class NoAffiliation implements Affiliation {
        @Override
        double calculateDeductions(PayCheque payCheque) {
            return 0
        }
    }
}
