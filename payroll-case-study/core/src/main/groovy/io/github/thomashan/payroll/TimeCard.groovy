package io.github.thomashan.payroll

import groovy.transform.Immutable

import java.time.LocalDate

@Immutable
class TimeCard {
    LocalDate date
    double hours
}
