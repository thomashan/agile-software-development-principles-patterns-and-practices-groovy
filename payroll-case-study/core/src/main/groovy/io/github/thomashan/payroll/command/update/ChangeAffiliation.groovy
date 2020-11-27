package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.affiliation.Affiliation

abstract class ChangeAffiliation extends ChangeEmployee {
    ChangeAffiliation(int employeeId) {
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
