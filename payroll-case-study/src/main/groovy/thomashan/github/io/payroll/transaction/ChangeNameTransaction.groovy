package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.Employee

class ChangeNameTransaction extends ChangeEmployeeTransaction {
    final String name

    ChangeNameTransaction(int employeeId, String name) {
        super(employeeId)
        this.name = name
    }

    @Override
    void change(Employee employee) {
        employee.name = name
    }
}
