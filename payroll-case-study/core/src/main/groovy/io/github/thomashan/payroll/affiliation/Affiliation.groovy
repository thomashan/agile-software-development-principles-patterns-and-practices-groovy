package io.github.thomashan.payroll.affiliation

trait Affiliation {
    abstract double calculateDeductions(io.github.thomashan.payroll.PayCheque payCheque)
}
