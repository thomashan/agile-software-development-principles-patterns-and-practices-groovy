package io.github.thomashan.payroll.transaction.update

class ChangeAddressCommand extends ChangeEmployeeCommand {
    final String address

    ChangeAddressCommand(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    void change(io.github.thomashan.payroll.Employee employee) {
        employee.address = address
    }
}
