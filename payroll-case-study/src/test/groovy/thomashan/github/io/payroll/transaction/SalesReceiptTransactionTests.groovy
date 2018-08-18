package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.transaction.add.AddCommissionedEmployee
import thomashan.github.io.payroll.transaction.add.AddHourlyEmployee
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class SalesReceiptTransactionTests implements TransactionTests {
    private LocalDate today = LocalDate.now()

    @Test
    void "sales receipt transaction on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new SaleReceiptTransaction(employeeId, today, 8).execute() })
    }

    @Test
    void "sales receipt transaction on hourly employee should throw an error"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()

        assertThrows(RuntimeException, { new SaleReceiptTransaction(employeeId, today, 8).execute() })
    }

    @Test
    void "sales receipt transaction on salaried employee should throw an error"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new SaleReceiptTransaction(employeeId, today, 8).execute() })
    }

    @Test
    void "sales receipt transaction on commissioned employee should execute without error"() {
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1000, 20).execute()

        new SaleReceiptTransaction(employeeId, today, 8).execute()
    }
}
