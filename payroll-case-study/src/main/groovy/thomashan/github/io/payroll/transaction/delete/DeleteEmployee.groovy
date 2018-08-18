package thomashan.github.io.payroll.transaction.delete

import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.transaction.Transaction

@TupleConstructor
class DeleteEmployee implements Transaction {
    final int employeeId

    @Override
    void execute() {
        payrollDatabase.deleteEmployee(employeeId)
    }
}
