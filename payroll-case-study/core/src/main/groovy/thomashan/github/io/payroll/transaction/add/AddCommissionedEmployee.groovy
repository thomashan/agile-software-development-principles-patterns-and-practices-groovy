package thomashan.github.io.payroll.transaction.add

import groovy.transform.Canonical
import thomashan.github.io.payroll.classification.CommissionedClassification
import thomashan.github.io.payroll.schedule.BiweeklySchedule

class AddCommissionedEmployee implements AddEmployeeTransaction {
    final double monthlySalary
    final double commissionRate

    AddCommissionedEmployee(int employeeId, String name, String address, double monthlySalary, double commissionRate) {
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__employeeId = employeeId
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__name = name
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__address = address
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__paymentClassification = new CommissionedClassification(monthlySalary, commissionRate)
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__paymentSchedule = BiweeklySchedule.instance
        this.monthlySalary = monthlySalary
        this.commissionRate = commissionRate
    }
}
