package thomashan.github.io.payroll.transaction.add

import groovy.transform.Canonical
import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.schedule.WeeklySchedule

@Canonical
class AddHourlyEmployee implements AddEmployeeCommand {
    final double hourlyRate

    AddHourlyEmployee(int employeeId, String name, String address, double hourlyRate) {
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeCommand__employeeId = employeeId
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeCommand__name = name
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeCommand__address = address
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeCommand__paymentClassification = new HourlyClassification(hourlyRate)
        this.thomashan_github_io_payroll_transaction_add_AddEmployeeCommand__paymentSchedule = WeeklySchedule.instance
        this.hourlyRate = hourlyRate
    }
}
