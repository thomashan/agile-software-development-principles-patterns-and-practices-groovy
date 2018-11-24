package thomashan.github.io.payroll.ui.zk.list


import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase
import thomashan.github.io.payroll.ui.zk.LoadTestEmployees

class ListEmployeeViewModel {
    PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    List<EmployeeViewModel> employees = []
    EmployeeViewModel selectedEmployee
    Date date = new Date()

    ListEmployeeViewModel() {
        LoadTestEmployees.load()
        this.employees = payrollDatabase.allEmployeeIds.collect {
            new EmployeeViewModel(payrollDatabase.getEmployee(it))
        }
    }
}
