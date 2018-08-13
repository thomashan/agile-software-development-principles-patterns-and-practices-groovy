package thomashan.github.io.payroll.transaction

import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase

@TupleConstructor
class DeleteEmployee implements Transaction {
    private final PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    final int employeeId

    @Override
    void execute() {
        payrollDatabase.deleteEmployee(employeeId)
    }
}
