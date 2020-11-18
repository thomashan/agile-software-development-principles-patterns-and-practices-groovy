package io.github.thomashan.payroll.classification

import groovy.transform.TupleConstructor

@TupleConstructor
class SalariedClassification implements PaymentClassification {
    final double salary

    @Override
    double calculatePay(io.github.thomashan.payroll.PayCheque payCheque) {
        return salary
    }
}
