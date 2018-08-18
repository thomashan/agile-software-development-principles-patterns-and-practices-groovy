package thomashan.github.io.payroll

import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.affiliation.Affiliation
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.method.PaymentMethod
import thomashan.github.io.payroll.schedule.PaymentSchedule

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
}
