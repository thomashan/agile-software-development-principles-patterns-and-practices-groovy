package io.github.thomashan.payroll.ui.zk.list

class ListEmployeeViewModel {
    io.github.thomashan.payroll.PayrollDatabase payrollDatabase = io.github.thomashan.payroll.InMemPayrollDatabase.instance
    List<EmployeeViewModel> employees = []
    EmployeeViewModel selectedEmployee
    Date date = new Date()

    ListEmployeeViewModel() {
        io.github.thomashan.payroll.ui.zk.LoadTestEmployees.load()
        this.employees = payrollDatabase.allEmployeeIds.collect {
            new EmployeeViewModel(payrollDatabase.getEmployee(it))
        }
    }
}
