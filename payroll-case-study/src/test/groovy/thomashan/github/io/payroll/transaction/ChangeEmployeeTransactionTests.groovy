package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee

import static org.junit.jupiter.api.Assertions.assertThrows

trait ChangeEmployeeTransactionTests extends TransactionTests {
    @Test
    void "execute on nonexistent employee throws an error"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new ChangeEmployeeTransactionImpl(2).execute() })
    }

    private static class ChangeEmployeeTransactionImpl extends ChangeEmployeeTransaction {
        ChangeEmployeeTransactionImpl(int employeeId) {
            super(employeeId)
        }

        @Override
        void change(Employee employee) {

        }
    }
}
