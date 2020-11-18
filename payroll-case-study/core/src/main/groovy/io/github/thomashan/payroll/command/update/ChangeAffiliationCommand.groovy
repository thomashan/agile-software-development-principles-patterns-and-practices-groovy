package io.github.thomashan.payroll.command.update

abstract class ChangeAffiliationCommand extends ChangeEmployeeCommand {
    ChangeAffiliationCommand(int employeeId) {
        super(employeeId)
    }

    @Override
    void change(io.github.thomashan.payroll.Employee employee) {
        recordMembership(employee)
        employee.affiliation = affiliation
    }

    abstract protected Optional<io.github.thomashan.payroll.affiliation.Affiliation> getAffiliation()

    abstract protected void recordMembership(io.github.thomashan.payroll.Employee employee)
}
