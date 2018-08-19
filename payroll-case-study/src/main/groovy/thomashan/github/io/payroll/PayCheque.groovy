package thomashan.github.io.payroll

import java.time.LocalDate

class PayCheque {
    final LocalDate payPeriodStartDate
    final LocalDate payPeriodEndDate
    final double grossPay
    final double deduction
    final double netPay

    PayCheque(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate) {
        this.payPeriodStartDate = payPeriodStartDate
        this.payPeriodEndDate = payPeriodEndDate
    }

    String getField(String field) {
        // FIXME: to implement
    }
}
