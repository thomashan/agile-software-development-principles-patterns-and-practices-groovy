package thomashan.github.io.payroll.transaction

import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.schedule.PaymentSchedule
import thomashan.github.io.payroll.schedule.WeeklySchedule

@Canonical
@TupleConstructor(includeSuperFields = true)
class AddHourlyEmployee extends AddEmployeeTransaction {
    final double hourlyRate

    @Override
    PaymentClassification getPaymentClassification() {
        return new HourlyClassification()
    }

    @Override
    PaymentSchedule getPaymentSchedule() {
        return new WeeklySchedule()
    }
}
