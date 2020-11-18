package io.github.thomashan.payroll.transaction.update

abstract class ChangeEmployeeCommand implements io.github.thomashan.payroll.transaction.Command {
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
