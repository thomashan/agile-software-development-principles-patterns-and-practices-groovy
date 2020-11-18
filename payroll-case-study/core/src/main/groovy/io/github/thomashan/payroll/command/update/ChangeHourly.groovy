package io.github.thomashan.payroll.command.update


import io.github.thomashan.payroll.classification.HourlyClassification
import io.github.thomashan.payroll.classification.PaymentClassification

class ChangeHourly extends ChangeClassification {
    final double hourlyRate

    ChangeHourly(int employeeId, double hourlyRate) {
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
