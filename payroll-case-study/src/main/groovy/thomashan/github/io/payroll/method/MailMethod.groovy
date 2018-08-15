package thomashan.github.io.payroll.method

import groovy.transform.Immutable

@Immutable
class MailMethod implements PaymentMethod {
    String address
}
