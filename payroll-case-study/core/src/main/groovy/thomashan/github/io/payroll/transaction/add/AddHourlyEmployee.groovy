package thomashan.github.io.payroll.transaction.add

import groovy.transform.Canonical
import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.schedule.WeeklySchedule

@Canonical
class AddHourlyEmployee implements AddEmployeeTransaction {
    final double hourlyRate

    AddHourlyEmployee(int employeeId, String name, String address, double hourlyRate) {
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__employeeId = employeeId
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__name = name
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__address = address
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__paymentClassification = new HourlyClassification(hourlyRate)
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeTransaction__paymentSchedule = WeeklySchedule.instance
        this.hourlyRate = hourlyRate
    }
}
