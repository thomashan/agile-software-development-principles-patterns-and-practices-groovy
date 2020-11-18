package io.github.thomashan.payroll.method

import groovy.transform.TupleConstructor
import jdk.nashorn.internal.ir.annotations.Immutable

@Immutable
@TupleConstructor
class DirectMethod implements PaymentMethod {
    String bank
    String account
}
