package io.github.thomashan.payroll.transaction.update


import io.github.thomashan.payroll.classification.HourlyClassification
import io.github.thomashan.payroll.classification.PaymentClassification

class ChangeHourlyCommand extends ChangeClassificationCommand {
    final double hourlyRate

    ChangeHourlyCommand(int employeeId, double hourlyRate) {
        super(employeeId)
        this.hourlyRate = hourlyRate
    }

    @Override
    PaymentClassification getPaymentClassification() {
        return new HourlyClassification(hourlyRate)
    }

    @Override
    io.github.thomashan.payroll.schedule.PaymentSchedule getPaymentSchedule() {
        return io.github.thomashan.payroll.schedule.WeeklySchedule.instance
    }
}
