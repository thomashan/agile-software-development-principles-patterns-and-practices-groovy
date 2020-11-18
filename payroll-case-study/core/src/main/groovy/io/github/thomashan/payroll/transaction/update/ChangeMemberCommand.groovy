package io.github.thomashan.payroll.transaction.update

class ChangeMemberCommand extends ChangeAffiliationCommand {
    final int memberId
    final double dues

    ChangeMemberCommand(int employeeId, int memberId, double dues) {
        super(employeeId)
        this.memberId = memberId
        this.dues = dues
    }

    @Override
    Optional<io.github.thomashan.payroll.affiliation.Affiliation> getAffiliation() {
        return Optional.of(new io.github.thomashan.payroll.affiliation.UnionAffiliation(memberId, dues))
    }

    @Override
    void recordMembership(io.github.thomashan.payroll.Employee employee) {
        payrollDatabase.addUnionMember(memberId, employee)
    }
}
