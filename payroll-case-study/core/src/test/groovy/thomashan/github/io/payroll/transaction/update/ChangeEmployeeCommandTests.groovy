package thomashan.github.io.payroll.transaction.update

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.transaction.CommandTests
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

import static org.junit.jupiter.api.Assertions.assertThrows

trait ChangeEmployeeCommandTests extends CommandTests {
    @Test
    void "execute on nonexistent employee throws an error"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new ChangeEmployeeCommandImpl(2).execute() })
    }

    private static class ChangeEmployeeCommandImpl extends ChangeEmployeeCommand {
        ChangeEmployeeCommandImpl(int employeeId) {
            super(employeeId)
        }

        @Override
        void change(Employee employee) {

        }
    }
}
