package io.github.thomashan.payroll.command.update

class ChangeName extends ChangeEmployee {
    final String name

    ChangeName(int employeeId, String name) {
        super(employeeId)
        this.name = name
    }

    @Override
    void change(io.github.thomashan.payroll.Employee employee) {
        employee.name = name
    }
}
