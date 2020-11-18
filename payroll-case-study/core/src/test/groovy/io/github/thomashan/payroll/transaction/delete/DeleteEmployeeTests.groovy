package io.github.thomashan.payroll.transaction.delete


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class DeleteEmployeeTests implements io.github.thomashan.payroll.transaction.CommandTests {
    @BeforeEach
    void setUp() {
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1).execute()
    }

    @Test
    void "delete existing employee should execute without error"() {
        new DeleteEmployee(employeeId).execute()

        assert payrollDatabase.getEmployee(employeeId) == null
    }

    @Test
    void "delete non-existing employee should throw an error"() {
        assertThrows(RuntimeException, { new DeleteEmployee(2).execute() })
    }
}
