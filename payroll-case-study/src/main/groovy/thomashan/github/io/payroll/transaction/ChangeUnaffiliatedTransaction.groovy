package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.affiliation.Affiliation
import thomashan.github.io.payroll.affiliation.UnionAffiliation

class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    ChangeUnaffiliatedTransaction(int employeeId) {
        super(employeeId)
    }

    @Override
    Optional<Affiliation> getAffiliation() {
        return Optional.empty()
    }

    @Override
    void recordMembership(Employee employee) {
        if (employee.affiliation.present && employee.affiliation.get() instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = employee.affiliation.get()
            payrollDatabase.removeUnionMember(unionAffiliation.memberId)
        }
    }
}
