package thomashan.github.io.payroll.transaction

import groovy.transform.Canonical
import thomashan.github.io.payroll.classification.CommissionedClassification
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.schedule.BiweeklySchedule
import thomashan.github.io.payroll.schedule.PaymentSchedule

@Canonical
class AddCommissionedEmployee extends AddEmployeeTransaction {
    final double monthlySalary
    final double commissionRate

    @Override
    PaymentClassification getPaymentClassification() {
        return new CommissionedClassification()
    }

    @Override
    PaymentSchedule getPaymentSchedule() {
        return new BiweeklySchedule()
    }
}
