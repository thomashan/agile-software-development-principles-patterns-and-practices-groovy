package io.github.thomashan.payroll.transaction.delete

import groovy.transform.TupleConstructor

@TupleConstructor
class DeleteEmployee implements io.github.thomashan.payroll.transaction.Command {
    final int employeeId

    @Override
    void execute() {
        payrollDatabase.deleteEmployee(employeeId)
    }
}
