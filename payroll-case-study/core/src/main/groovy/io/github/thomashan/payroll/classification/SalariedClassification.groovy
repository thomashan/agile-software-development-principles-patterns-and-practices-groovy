package io.github.thomashan.payroll.classification

import groovy.transform.EqualsAndHashCode
import groovy.transform.TupleConstructor
import io.github.thomashan.payroll.PayCheque

@TupleConstructor
@EqualsAndHashCode
class SalariedClassification implements PaymentClassification {
    final double salary

    @Override
    double calculatePay(PayCheque payCheque) {
        return salary
    }
}
