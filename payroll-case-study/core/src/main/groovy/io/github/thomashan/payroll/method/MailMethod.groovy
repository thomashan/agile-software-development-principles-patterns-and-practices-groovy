package io.github.thomashan.payroll.method

import groovy.transform.Immutable
import groovy.transform.TupleConstructor

@Immutable
@TupleConstructor
class MailMethod implements PaymentMethod {
    String address
}
