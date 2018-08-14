package thomashan.github.io.payroll

import groovy.transform.Immutable

import java.time.LocalDate

@Immutable
class SalesReceipt {
    LocalDate date
    double amount
}
