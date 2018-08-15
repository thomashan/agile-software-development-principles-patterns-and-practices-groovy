package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.Employee

class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    final String address

    ChangeAddressTransaction(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    void change(Employee employee) {
        employee.address = address
    }
}
