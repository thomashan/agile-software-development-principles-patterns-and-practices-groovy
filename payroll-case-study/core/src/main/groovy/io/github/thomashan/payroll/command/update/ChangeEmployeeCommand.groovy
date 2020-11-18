package io.github.thomashan.payroll.command.update

import io.github.thomashan.command.Command

abstract class ChangeEmployeeCommand implements Command {
    final int employeeId

    ChangeEmployeeCommand(int employeeId) {
        this.employeeId = employeeId
    }

    @Override
    void execute() {
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        if (!employee) {
            throw new RuntimeException("No such employee")
        }

        change(employee)
    }

    abstract void change(io.github.thomashan.payroll.Employee employee)
}
