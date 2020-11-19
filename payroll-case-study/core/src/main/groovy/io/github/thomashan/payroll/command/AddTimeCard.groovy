package io.github.thomashan.payroll.command

import io.github.thomashan.command.Command
import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.TimeCard
import io.github.thomashan.payroll.classification.HourlyClassification

import java.time.LocalDate

class AddTimeCard implements Command {
    final int employeeId
    final LocalDate date
    final double hours

    AddTimeCard(int employeeId, LocalDate date, double hours) {
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
