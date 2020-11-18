package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.affiliation.Affiliation
import thomashan.github.io.payroll.affiliation.UnionAffiliation

class ChangeUnaffiliatedCommand extends ChangeAffiliationCommand {
    ChangeUnaffiliatedCommand(int employeeId) {
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
