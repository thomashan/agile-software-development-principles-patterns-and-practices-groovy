package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee

class ChangeAddress extends ChangeEmployee {
    final String address

    ChangeAddress(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    void change(Employee employee) {
        employee.address = address
    }
}
