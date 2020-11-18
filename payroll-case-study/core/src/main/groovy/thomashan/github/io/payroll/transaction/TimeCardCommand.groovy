package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.TimeCard
import thomashan.github.io.payroll.classification.HourlyClassification

import java.time.LocalDate

class TimeCardCommand implements Command {
    final int employeeId
    final LocalDate date
    final double hours

    TimeCardCommand(int employeeId, LocalDate date, double hours) {
        this.employeeId = employeeId
        this.date = date
        this.hours = hours
    }

    @Override
    void execute() {
        Employee employee = payrollDatabase.getEmployee(employeeId)

        if (employee) {
            if (employee.paymentClassification instanceof HourlyClassification) {
                HourlyClassification hourlyClassification = employee.paymentClassification
                hourlyClassification.addTimeCard(new TimeCard(date, hours))
            } else {
                throw new RuntimeException("Error adding time card to non-hourly employee")
            }
        } else {
            throw new RuntimeException("No such employee")
        }
    }
}
