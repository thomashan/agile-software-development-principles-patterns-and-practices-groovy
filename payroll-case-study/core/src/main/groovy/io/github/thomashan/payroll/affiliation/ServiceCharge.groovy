package io.github.thomashan.payroll.affiliation

import groovy.transform.Immutable

import java.time.LocalDate

@Immutable
class ServiceCharge {
    LocalDate date
    double amount
}
