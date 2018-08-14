package thomashan.github.io.payroll.transaction

import groovy.transform.TupleConstructor

@TupleConstructor
class DeleteEmployee implements Transaction {
    final int employeeId

    @Override
    void execute() {
        payrollDatabase.deleteEmployee(employeeId)
    }
}
