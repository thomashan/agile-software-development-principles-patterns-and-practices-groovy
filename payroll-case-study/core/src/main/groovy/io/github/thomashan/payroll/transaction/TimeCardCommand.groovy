package io.github.thomashan.payroll.transaction


import io.github.thomashan.payroll.classification.HourlyClassification

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
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)

        if (employee) {
            if (employee.paymentClassification instanceof HourlyClassification) {
                HourlyClassification hourlyClassification = employee.paymentClassification
                hourlyClassification.addTimeCard(new io.github.thomashan.payroll.TimeCard(date, hours))
            } else {
                throw new RuntimeException("Error adding time card to non-hourly employee")
            }
        } else {
            throw new RuntimeException("No such employee")
        }
    }
}
