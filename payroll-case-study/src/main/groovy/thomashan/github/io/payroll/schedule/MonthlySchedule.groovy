package thomashan.github.io.payroll.schedule

import groovy.transform.Immutable

import java.time.LocalDate

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth

@Immutable
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
