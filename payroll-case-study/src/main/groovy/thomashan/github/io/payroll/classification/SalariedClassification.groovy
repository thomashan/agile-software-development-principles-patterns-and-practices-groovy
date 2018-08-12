package thomashan.github.io.payroll.classification

import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.classification.PaymentClassification

@TupleConstructor
class SalariedClassification extends PaymentClassification {
    final double salary
}
