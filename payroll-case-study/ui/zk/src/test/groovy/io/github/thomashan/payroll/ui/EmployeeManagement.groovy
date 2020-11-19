package io.github.thomashan.payroll.ui

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.PayrollDatabaseInMemory
import io.github.thomashan.payroll.PayrollDatabase
import org.junit.jupiter.api.AfterEach

trait EmployeeManagement {
    final PayrollDatabase payrollDatabase = PayrollDatabaseInMemory.instance
    final int employeeId = 1

    @AfterEach
    void tearDown() {
        Employee employee = payrollDatabase.getEmployee(employeeId)
        if (employee) {
            payrollDatabase.deleteEmployee(employeeId)
        }
    }
}
