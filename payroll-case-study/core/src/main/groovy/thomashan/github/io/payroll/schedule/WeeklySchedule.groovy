package thomashan.github.io.payroll.schedule

import java.time.LocalDate

import static java.time.DayOfWeek.FRIDAY

@Singleton
class WeeklySchedule implements PaymentSchedule {
    @Override
    boolean isPayDate(LocalDate payDate) {
        return payDate.dayOfWeek == FRIDAY
    }

    @Override
    LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return payDate.minusWeeks(1)
    }
}
