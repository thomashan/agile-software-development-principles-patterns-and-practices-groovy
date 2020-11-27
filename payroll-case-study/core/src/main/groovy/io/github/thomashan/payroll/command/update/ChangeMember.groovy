package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.affiliation.Affiliation
import io.github.thomashan.payroll.affiliation.UnionAffiliation

class ChangeMember extends ChangeAffiliation {
    final int memberId
    final double dues

    ChangeMember(int employeeId, int memberId, double dues) {
        super(employeeId)
        this.memberId = memberId
        this.dues = dues
    }

    @Override
    Optional<Affiliation> getAffiliation() {
        return Optional.of(new UnionAffiliation(memberId, dues))
    }

    @Override
    void recordMembership(Employee employee) {
        payrollDatabase.addUnionMember(memberId, employee)
    }
}
