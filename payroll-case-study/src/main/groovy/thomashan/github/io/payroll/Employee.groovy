package thomashan.github.io.payroll

import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.affiliation.Affiliation
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.method.PaymentMethod
import thomashan.github.io.payroll.schedule.PaymentSchedule

import java.time.LocalDate

@Canonical
@TupleConstructor
class Employee {
    final int employeeId
    String name
    String address
    PaymentClassification paymentClassification
    PaymentSchedule paymentSchedule
    PaymentMethod paymentMethod
    Optional<Affiliation> affiliation = Optional.empty()

    boolean isPayDate(LocalDate payDate) {
        return paymentSchedule.isPayDate(payDate)
    }

    void payday(PayCheque payCheque) {
        double grossPay = paymentClassification.calculatePay(payCheque)
        double deductions = affiliation.map { it.calculateDeductions(payCheque) }
                .orElse(0)

        payCheque.grossPay = grossPay
        payCheque.deductions = deductions
        payCheque.netPay = grossPay - deductions
    }

    LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
        return paymentSchedule.getPayPeriodStartDate(payPeriodEndDate)
    }
}
