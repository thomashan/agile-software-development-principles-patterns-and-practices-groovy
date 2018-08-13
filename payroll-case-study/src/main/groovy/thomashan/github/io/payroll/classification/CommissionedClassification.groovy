package thomashan.github.io.payroll.classification

import groovy.transform.TupleConstructor

@TupleConstructor
class CommissionedClassification extends PaymentClassification {
    final double salary
    final double commissionRate
}
