package io.github.thomashan.payroll.classification

trait PaymentClassification {
    abstract double calculatePay(io.github.thomashan.payroll.PayCheque payCheque)
}
