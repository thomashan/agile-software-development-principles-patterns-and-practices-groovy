package io.github.thomashan.payroll.command.update

import io.github.thomashan.command.CommandTests
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

trait ChangeEmployeeTests extends CommandTests {
    @Test
    void "execute on nonexistent employee throws an error"() {
        new io.github.thomashan.payroll.command.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new ChangeEmployeeImpl(2).execute() })
    }

    private static class ChangeEmployeeImpl extends ChangeEmployee {
        ChangeEmployeeImpl(int employeeId) {
            super(employeeId)
        }

        @Override
        void change(io.github.thomashan.payroll.Employee employee) {

        }
    }
}
