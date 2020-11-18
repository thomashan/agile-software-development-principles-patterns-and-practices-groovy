package io.github.thomashan.payroll.schedule

import java.time.LocalDate

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth

@Singleton
class MonthlySchedule implements PaymentSchedule {
    @Override
    boolean isPayDate(LocalDate payDate) {
        LocalDate lastDayOfMonth = payDate.with(lastDayOfMonth())

        return payDate == lastDayOfMonth
    }

    @Override
    LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return payDate.minusMonths(1)
    }
}
