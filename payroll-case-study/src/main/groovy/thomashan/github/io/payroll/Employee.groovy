package thomashan.github.io.payroll

import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.method.PaymentMethod
import thomashan.github.io.payroll.schedule.PaymentSchedule

@Canonical
@TupleConstructor
class Employee {
    final int employeeId
    final String name
    final String address
    final PaymentClassification paymentClassification
    final PaymentSchedule paymentSchedule
    final PaymentMethod paymentMethod
}
