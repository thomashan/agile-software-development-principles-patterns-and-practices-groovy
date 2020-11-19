package io.github.thomashan.payroll.affiliation

import io.github.thomashan.payroll.PayCheque

trait Affiliation {
    abstract double calculateDeductions(PayCheque payCheque)
}
