package thomashan.github.io.payroll.classification

import thomashan.github.io.payroll.PayCheque

trait PaymentClassification {
    abstract double calculatePay(PayCheque payCheque)
}
