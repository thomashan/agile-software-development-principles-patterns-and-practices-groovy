package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase
import thomashan.github.io.payroll.TimeCard
import thomashan.github.io.payroll.classification.HourlyClassification

import java.time.LocalDate

class TimeCardTransaction implements Transaction {
    private final PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    final int employeeId
    final LocalDate date
    final double hours

    TimeCardTransaction(int employeeId, LocalDate date, double hours) {
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
                throw new RuntimeException("Tried to add tim card to non-hourly employee")
            }
        } else {
            throw new RuntimeException("No such employee")
        }
    }
}
