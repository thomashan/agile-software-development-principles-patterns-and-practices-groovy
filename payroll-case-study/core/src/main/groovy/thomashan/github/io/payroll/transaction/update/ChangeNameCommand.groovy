package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.Employee

class ChangeNameCommand extends ChangeEmployeeCommand {
    final String name

    ChangeNameCommand(int employeeId, String name) {
        super(employeeId)
        this.name = name
    }

    @Override
    void change(Employee employee) {
        employee.name = name
    }
}
