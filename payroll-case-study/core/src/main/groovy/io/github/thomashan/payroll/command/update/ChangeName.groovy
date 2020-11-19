package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee

class ChangeName extends ChangeEmployee {
    final String name

    ChangeName(int employeeId, String name) {
        super(employeeId)
        this.name = name
    }

    @Override
    void change(Employee employee) {
        employee.name = name
    }
}
