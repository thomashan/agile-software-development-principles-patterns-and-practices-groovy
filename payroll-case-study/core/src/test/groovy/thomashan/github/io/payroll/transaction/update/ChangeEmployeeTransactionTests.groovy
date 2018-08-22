package thomashan.github.io.payroll.transaction.update

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.transaction.TransactionTests
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee
import thomashan.github.io.payroll.transaction.update.ChangeEmployeeTransaction

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
