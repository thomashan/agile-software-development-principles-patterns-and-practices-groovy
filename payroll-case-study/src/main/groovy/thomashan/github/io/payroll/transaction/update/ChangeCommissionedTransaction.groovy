package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.classification.CommissionedClassification
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.schedule.BiweeklySchedule
import thomashan.github.io.payroll.schedule.PaymentSchedule

class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    final double salary
    final double commissionRate

    ChangeCommissionedTransaction(int employeeId, double salary, double commissionRate) {
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
        return new BiweeklySchedule()
    }
}
