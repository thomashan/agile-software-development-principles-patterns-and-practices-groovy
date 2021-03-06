package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.schedule.PaymentSchedule
import thomashan.github.io.payroll.schedule.WeeklySchedule

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
    PaymentSchedule getPaymentSchedule() {
        return WeeklySchedule.instance
    }
}
