package io.github.thomashan.payroll.transaction.update


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class ChangeUnaffiliatedCommandTests implements ChangeEmployeeCommandTests {
    private int memberId = 1

    @BeforeEach
    void setUp() {
        new io.github.thomashan.payroll.transaction.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
    }

    @Test
    void "change unaffiliated transaction should execute without error"() {
        double dues = 10
        new ChangeMemberCommand(employeeId, memberId, dues).execute()

        new ChangeUnaffiliatedCommand(employeeId).execute()

        assert payrollDatabase.getUnionMember(memberId) == null
    }

    @Test
    void "change unaffiliated transaction should show an error if the employee has no affiliation"() {
        new io.github.thomashan.payroll.transaction.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()

        assertThrows(RuntimeException, { new ChangeUnaffiliatedCommand(employeeId).execute() })
    }

    @Test
    void "change unaffiliated transaction should show an error if the employee is not affiliated with union"() {
        new io.github.thomashan.payroll.transaction.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        employee.affiliation = Optional.of(new NoAffiliation())

        assertThrows(RuntimeException, { new ChangeUnaffiliatedCommand(employeeId).execute() })
    }

    private static class NoAffiliation implements io.github.thomashan.payroll.affiliation.Affiliation {
        @Override
        double calculateDeductions(io.github.thomashan.payroll.PayCheque payCheque) {
            return 0
        }
    }
}
