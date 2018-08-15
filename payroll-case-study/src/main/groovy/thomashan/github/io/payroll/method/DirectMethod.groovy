package thomashan.github.io.payroll.method

import jdk.nashorn.internal.ir.annotations.Immutable

@Immutable
class DirectMethod implements PaymentMethod {
    String bank
    String account
}
