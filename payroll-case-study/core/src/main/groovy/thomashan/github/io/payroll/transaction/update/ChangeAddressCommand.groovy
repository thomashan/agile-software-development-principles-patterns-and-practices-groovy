package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.Employee

class ChangeAddressCommand extends ChangeEmployeeCommand {
    final String address

    ChangeAddressCommand(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    void change(Employee employee) {
        employee.address = address
    }
}
