package io.github.thomashan.payroll

import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import io.github.thomashan.payroll.affiliation.Affiliation
import io.github.thomashan.payroll.classification.PaymentClassification
import io.github.thomashan.payroll.method.PaymentMethod
import io.github.thomashan.payroll.schedule.PaymentSchedule

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
