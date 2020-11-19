package io.github.thomashan.payroll.ui.zk.list

import io.github.thomashan.payroll.PayrollDatabaseInMemory
import io.github.thomashan.payroll.PayrollDatabase
import io.github.thomashan.payroll.ui.zk.LoadTestEmployees

class ListEmployeeViewModel {
    PayrollDatabase payrollDatabase = PayrollDatabaseInMemory.instance
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
