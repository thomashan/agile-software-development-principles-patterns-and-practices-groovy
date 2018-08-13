package thomashan.github.io.payroll.classification

import groovy.transform.TupleConstructor

@TupleConstructor
class HourlyClassification extends PaymentClassification {
    final double hourlyRate
}
