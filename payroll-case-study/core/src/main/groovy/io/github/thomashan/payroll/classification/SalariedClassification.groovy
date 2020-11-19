package io.github.thomashan.payroll.classification

import groovy.transform.TupleConstructor
import io.github.thomashan.payroll.PayCheque

@TupleConstructor
class SalariedClassification implements PaymentClassification {
    final double salary

    @Override
    double calculatePay(PayCheque payCheque) {
        return salary
    }
}
