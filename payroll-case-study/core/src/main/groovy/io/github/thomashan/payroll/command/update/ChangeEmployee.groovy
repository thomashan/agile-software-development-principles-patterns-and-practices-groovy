package io.github.thomashan.payroll.command.update

import io.github.thomashan.command.Command
import io.github.thomashan.payroll.Employee

abstract class ChangeEmployee implements Command {
    final int employeeId

    ChangeEmployee(int employeeId) {
        this.employeeId = employeeId
    }

    @Override
    void execute() {
        Employee employee = payrollDatabase.getEmployee(employeeId)
        if (!employee) {
            throw new RuntimeException("No such employee")
        }

        change(employee)
    }

    abstract void change(Employee employee)
}
