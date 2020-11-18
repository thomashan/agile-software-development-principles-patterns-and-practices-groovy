package thomashan.github.io.payroll.transaction.delete

import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.transaction.Command

@TupleConstructor
class DeleteEmployee implements Command {
    final int employeeId

    @Override
    void execute() {
        payrollDatabase.deleteEmployee(employeeId)
    }
}
