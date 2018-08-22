package thomashan.github.io.payroll.classification

import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.PayCheque
import thomashan.github.io.payroll.classification.PaymentClassification

@TupleConstructor
class SalariedClassification implements PaymentClassification {
    final double salary

    @Override
    double calculatePay(PayCheque payCheque) {
        return salary
    }
}
