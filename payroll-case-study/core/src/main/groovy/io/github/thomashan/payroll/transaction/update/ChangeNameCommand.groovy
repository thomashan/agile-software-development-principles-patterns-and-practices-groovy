package io.github.thomashan.payroll.transaction.update

class ChangeNameCommand extends ChangeEmployeeCommand {
    final String name

    ChangeNameCommand(int employeeId, String name) {
        super(employeeId)
        this.name = name
    }

    @Override
    void change(io.github.thomashan.payroll.Employee employee) {
        employee.name = name
    }
}
