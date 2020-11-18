package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.transaction.Command

abstract class ChangeEmployeeCommand implements Command {
    final int employeeId

    ChangeEmployeeCommand(int employeeId) {
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
