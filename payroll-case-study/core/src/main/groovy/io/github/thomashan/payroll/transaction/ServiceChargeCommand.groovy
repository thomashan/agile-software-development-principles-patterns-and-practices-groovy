package io.github.thomashan.payroll.transaction


import java.time.LocalDate

class ServiceChargeCommand implements Command {
    final int memberId
    final LocalDate date
    final double amount

    ServiceChargeCommand(int memberId, LocalDate date, double amount) {
        this.memberId = memberId
        this.date = date
        this.amount = amount
    }

    @Override
    void execute() {
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getUnionMember(memberId)
        if (!employee) {
            throw new RuntimeException("No such union member")
        }

        ((io.github.thomashan.payroll.affiliation.UnionAffiliation) employee.affiliation.get()).addServiceCharge(date, amount)
    }
}
