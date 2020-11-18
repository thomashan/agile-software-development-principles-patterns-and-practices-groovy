package io.github.thomashan.payroll.ui


import org.junit.jupiter.api.AfterEach

trait EmployeeManagement {
    final io.github.thomashan.payroll.PayrollDatabase payrollDatabase = io.github.thomashan.payroll.InMemPayrollDatabase.instance
    final int employeeId = 1

    @AfterEach
    void tearDown() {
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        if (employee) {
            payrollDatabase.deleteEmployee(employeeId)
        }
    }
}
