package thomashan.github.io.payroll.ui.zk.list

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.transaction.TransactionTests
import thomashan.github.io.payroll.transaction.add.AddCommissionedEmployee
import thomashan.github.io.payroll.transaction.add.AddHourlyEmployee
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

class EmployeeViewModelTests implements TransactionTests {
    @Test
    void "hourly employee wage should show correctly"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 1).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "1.0 / hour"
    }

    @Test
    void "salaried employee wage should show correctly"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "1.0 / month"
    }

    @Test
    void "commissioned employee wage should show correctly"() {
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1, 15).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "1.0 / month + 15.0% sales"
    }
}
