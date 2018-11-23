package thomashan.github.io.payroll.ui

import org.junit.jupiter.api.AfterEach
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase

trait EmployeeManagement {
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
