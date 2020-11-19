package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.affiliation.Affiliation
import io.github.thomashan.payroll.affiliation.UnionAffiliation

class ChangeUnaffiliated extends ChangeAffiliation {
    ChangeUnaffiliated(int employeeId) {
        super(employeeId)
    }

    @Override
    Optional<Affiliation> getAffiliation() {
        return Optional.empty()
    }

    @Override
    void recordMembership(Employee employee) {
        if (!employee.affiliation.present || !(employee.affiliation.get() instanceof UnionAffiliation)) {
            throw new RuntimeException("Employee employeeId[${employeeId}] not a union member")
        }

        UnionAffiliation unionAffiliation = employee.affiliation.get()
        payrollDatabase.removeUnionMember(unionAffiliation.memberId)
    }
}
