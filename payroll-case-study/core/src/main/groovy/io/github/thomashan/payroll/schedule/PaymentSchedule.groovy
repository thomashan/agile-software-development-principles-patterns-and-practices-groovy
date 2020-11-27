package io.github.thomashan.payroll.schedule

import java.time.LocalDate

trait PaymentSchedule {
    abstract boolean isPayDate(LocalDate payDate)

    abstract LocalDate getPayPeriodStartDate(LocalDate payDate)
}
