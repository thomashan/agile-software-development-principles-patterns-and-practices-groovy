package io.github.thomashan.payroll.command.update


import io.github.thomashan.payroll.classification.CommissionedClassification
import io.github.thomashan.payroll.classification.PaymentClassification
import io.github.thomashan.payroll.schedule.BiweeklySchedule
import io.github.thomashan.payroll.schedule.PaymentSchedule

class ChangeCommissioned extends ChangeClassification {
    final double salary
    final double commissionRate

    ChangeCommissioned(int employeeId, double salary, double commissionRate) {
        super(employeeId)
        this.salary = salary
        this.commissionRate = commissionRate
    }

    @Override
    PaymentClassification getPaymentClassification() {
        return new CommissionedClassification(salary, commissionRate)
    }

    @Override
    PaymentSchedule getPaymentSchedule() {
        return BiweeklySchedule.instance
    }
}
