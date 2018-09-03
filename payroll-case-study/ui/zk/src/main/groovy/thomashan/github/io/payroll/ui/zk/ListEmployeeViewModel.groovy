package thomashan.github.io.payroll.ui.zk

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase

class ListEmployeeViewModel {
    private PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    List<Employee> employees = LoadTestEmployees.load()
    Employee selectedEmployee
    Date date = new Date()

    ListEmployeeViewModel() {
        LoadTestEmployees.load()
        this.employees = payrollDatabase.allEmployeeIds.collect { payrollDatabase.getEmployee(it) }
    }
}
