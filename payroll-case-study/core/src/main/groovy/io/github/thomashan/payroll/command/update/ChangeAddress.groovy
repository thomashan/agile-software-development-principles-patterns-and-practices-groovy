package io.github.thomashan.payroll.command.update

class ChangeAddress extends ChangeEmployee {
    final String address

    ChangeAddress(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    void change(io.github.thomashan.payroll.Employee employee) {
        employee.address = address
    }
}
