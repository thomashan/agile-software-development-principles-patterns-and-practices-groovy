package io.github.thomashan.payroll

import java.time.LocalDate

class PayCheque {
    final LocalDate payPeriodStartDate
    final LocalDate payPeriodEndDate
    final LocalDate payDate
    double grossPay
    double deductions
    double netPay

    PayCheque(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate) {
        this.payPeriodStartDate = payPeriodStartDate
        this.payPeriodEndDate = payPeriodEndDate
        this.payDate = payPeriodEndDate
    }
}
