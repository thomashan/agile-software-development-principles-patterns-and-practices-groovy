package io.github.thomashan.payroll.command

import io.github.thomashan.command.CommandTests
import io.github.thomashan.payroll.command.add.AddCommissionedEmployee
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class AddSalesReceiptTests implements CommandTests {
    private LocalDate today = LocalDate.now()

    @Test
    void "sales receipt command on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new AddSalesReceipt(employeeId, today, 8).execute() })
    }

    @Test
    void "sales receipt command on hourly employee should throw an error"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()

        assertThrows(RuntimeException, { new AddSalesReceipt(employeeId, today, 8).execute() })
    }

    @Test
    void "sales receipt command on salaried employee should throw an error"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new AddSalesReceipt(employeeId, today, 8).execute() })
    }

    @Test
    void "sales receipt command on commissioned employee should execute without error"() {
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1000, 20).execute()

        new AddSalesReceipt(employeeId, today, 8).execute()
    }
}
