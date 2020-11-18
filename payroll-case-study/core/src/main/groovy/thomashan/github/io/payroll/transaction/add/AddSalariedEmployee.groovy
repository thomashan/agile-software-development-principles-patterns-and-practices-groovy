package thomashan.github.io.payroll.transaction.add

import groovy.transform.Canonical
import thomashan.github.io.payroll.classification.SalariedClassification
import thomashan.github.io.payroll.schedule.MonthlySchedule

@Canonical
class AddSalariedEmployee implements AddEmployeeTransaction {
    final double monthlySalary

    AddSalariedEmployee(int employeeId, String name, String address, double monthlySalary) {
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__employeeId = employeeId
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__name = name
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__address = address
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__paymentClassification = new SalariedClassification(monthlySalary)
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__paymentSchedule = MonthlySchedule.instance
        this.monthlySalary = monthlySalary
    }
}
