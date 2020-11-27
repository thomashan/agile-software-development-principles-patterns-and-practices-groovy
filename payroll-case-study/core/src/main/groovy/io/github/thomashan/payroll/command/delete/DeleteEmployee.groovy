package io.github.thomashan.payroll.command.delete

import groovy.transform.TupleConstructor
import io.github.thomashan.command.Command

@TupleConstructor
class DeleteEmployee implements Command {
    final int employeeId

    @Override
    void execute() {
        payrollDatabase.deleteEmployee(employeeId)
    }
}
