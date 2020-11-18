package io.github.thomashan.payroll.command.update

class ChangeMember extends ChangeAffiliation {
    final int memberId
    final double dues

    ChangeMember(int employeeId, int memberId, double dues) {
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
