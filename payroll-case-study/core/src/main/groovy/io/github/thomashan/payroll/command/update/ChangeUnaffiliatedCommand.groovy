package io.github.thomashan.payroll.command.update

class ChangeUnaffiliatedCommand extends ChangeAffiliationCommand {
    ChangeUnaffiliatedCommand(int employeeId) {
        super(employeeId)
    }

    @Override
    Optional<io.github.thomashan.payroll.affiliation.Affiliation> getAffiliation() {
        return Optional.empty()
    }

    @Override
    void recordMembership(io.github.thomashan.payroll.Employee employee) {
        if (!employee.affiliation.present || !(employee.affiliation.get() instanceof io.github.thomashan.payroll.affiliation.UnionAffiliation)) {
            throw new RuntimeException("Employee employeeId[${employeeId}] not a union member")
        }

        io.github.thomashan.payroll.affiliation.UnionAffiliation unionAffiliation = employee.affiliation.get()
        payrollDatabase.removeUnionMember(unionAffiliation.memberId)
    }
}
