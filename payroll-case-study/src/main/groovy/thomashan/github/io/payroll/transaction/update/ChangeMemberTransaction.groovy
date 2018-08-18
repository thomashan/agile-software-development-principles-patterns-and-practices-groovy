package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.affiliation.Affiliation
import thomashan.github.io.payroll.affiliation.UnionAffiliation

class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    final int memberId
    final double dues

    ChangeMemberTransaction(int employeeId, int memberId, double dues) {
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
