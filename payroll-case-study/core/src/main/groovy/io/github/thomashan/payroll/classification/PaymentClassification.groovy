package io.github.thomashan.payroll.classification

import io.github.thomashan.payroll.PayCheque

trait PaymentClassification {
    abstract double calculatePay(PayCheque payCheque)
}
