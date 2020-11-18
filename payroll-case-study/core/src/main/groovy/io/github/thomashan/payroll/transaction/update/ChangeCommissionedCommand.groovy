package io.github.thomashan.payroll.transaction.update


import io.github.thomashan.payroll.classification.CommissionedClassification
import io.github.thomashan.payroll.classification.PaymentClassification

class ChangeCommissionedCommand extends ChangeClassificationCommand {
    final double salary
    final double commissionRate

    ChangeCommissionedCommand(int employeeId, double salary, double commissionRate) {
        super(employeeId)
        this.salary = salary
        this.commissionRate = commissionRate
    }

    @Override
    PaymentClassification getPaymentClassification() {
        return new CommissionedClassification(salary, commissionRate)
    }

    @Override
    io.github.thomashan.payroll.schedule.PaymentSchedule getPaymentSchedule() {
        return io.github.thomashan.payroll.schedule.BiweeklySchedule.instance
    }
}
