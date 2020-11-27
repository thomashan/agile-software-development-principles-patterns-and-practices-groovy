package io.github.thomashan.payroll.command

import io.github.thomashan.command.Command
import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.affiliation.UnionAffiliation

import java.time.LocalDate

class AddServiceCharge implements Command {
    final int memberId
    final LocalDate date
    final double amount

    AddServiceCharge(int memberId, LocalDate date, double amount) {
        this.memberId = memberId
        this.date = date
        this.amount = amount
    }

    @Override
    void execute() {
        Employee employee = payrollDatabase.getUnionMember(memberId)
        if (!employee) {
            throw new RuntimeException("No such union member")
        }

        ((UnionAffiliation) employee.affiliation.get()).addServiceCharge(date, amount)
    }
}
