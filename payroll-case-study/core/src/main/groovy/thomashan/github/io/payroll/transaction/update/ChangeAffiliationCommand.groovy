package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.affiliation.Affiliation

abstract class ChangeAffiliationCommand extends ChangeEmployeeCommand {
    ChangeAffiliationCommand(int employeeId) {
        super(employeeId)
    }

    @Override
    void change(Employee employee) {
        recordMembership(employee)
        employee.affiliation = affiliation
    }

    abstract protected Optional<Affiliation> getAffiliation()

    abstract protected void recordMembership(Employee employee)
}
