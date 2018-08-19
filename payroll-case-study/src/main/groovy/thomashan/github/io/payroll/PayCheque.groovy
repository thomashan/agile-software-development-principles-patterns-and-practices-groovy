package thomashan.github.io.payroll

import java.time.LocalDate

class PayCheque {
    final LocalDate payDate
    final double grossPay
    final double deduction
    final double netPay

    PayCheque(LocalDate payDate) {
        this.payDate = payDate
    }

    String getField(String field) {
        // FIXME: to implement
    }
}
