package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.Employee

abstract class ChangeEmployeeTransaction implements Transaction {
    final int employeeId

    ChangeEmployeeTransaction(int employeeId) {
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

    abstract protected void change(Employee employee)
}
