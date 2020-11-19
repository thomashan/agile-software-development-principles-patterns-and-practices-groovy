package io.github.thomashan.command

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.InMemPayrollDatabase
import io.github.thomashan.payroll.PayrollDatabase
import org.junit.jupiter.api.AfterEach

trait CommandTests {
    final PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    final int employeeId = 1

    @AfterEach
    void tearDown() {
        Employee employee = payrollDatabase.getEmployee(employeeId)
        if (employee) {
            payrollDatabase.deleteEmployee(employeeId)
        }
    }
}
