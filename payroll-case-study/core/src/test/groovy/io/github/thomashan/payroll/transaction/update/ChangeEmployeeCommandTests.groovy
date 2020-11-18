package io.github.thomashan.payroll.transaction.update


import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

trait ChangeEmployeeCommandTests extends io.github.thomashan.payroll.transaction.CommandTests {
    @Test
    void "execute on nonexistent employee throws an error"() {
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new ChangeEmployeeCommandImpl(2).execute() })
    }

    private static class ChangeEmployeeCommandImpl extends ChangeEmployeeCommand {
        ChangeEmployeeCommandImpl(int employeeId) {
            super(employeeId)
        }

        @Override
        void change(io.github.thomashan.payroll.Employee employee) {

        }
    }
}
