package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase
import thomashan.github.io.payroll.affiliation.UnionAffiliation

import java.time.LocalDate

class ServiceChargeTransaction implements Transaction {
    private final PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    final int memberId
    final LocalDate date
    final double amount

    ServiceChargeTransaction(int memberId, LocalDate date, double amount) {
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
