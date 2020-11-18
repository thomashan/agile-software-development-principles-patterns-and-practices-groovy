package io.github.thomashan.payroll.transaction


import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class SalesReceiptCommandTests implements CommandTests {
    private LocalDate today = LocalDate.now()

    @Test
    void "sales receipt transaction on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new SaleReceiptCommand(employeeId, today, 8).execute() })
    }

    @Test
    void "sales receipt transaction on hourly employee should throw an error"() {
        new io.github.thomashan.payroll.transaction.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()

        assertThrows(RuntimeException, { new SaleReceiptCommand(employeeId, today, 8).execute() })
    }

    @Test
    void "sales receipt transaction on salaried employee should throw an error"() {
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new SaleReceiptCommand(employeeId, today, 8).execute() })
    }

    @Test
    void "sales receipt transaction on commissioned employee should execute without error"() {
        new io.github.thomashan.payroll.transaction.add.AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1000, 20).execute()

        new SaleReceiptCommand(employeeId, today, 8).execute()
    }
}
