package thomashan.github.io.payroll.schedule

import java.time.LocalDate
import java.time.temporal.WeekFields

import static java.time.DayOfWeek.FRIDAY

@Singleton
class BiweeklySchedule implements PaymentSchedule {
    @Override
    boolean isPayDate(LocalDate payDate) {
        int weekOfYear = payDate.get(WeekFields.of(Locale.default).weekOfWeekBasedYear())
        return payDate.dayOfWeek == FRIDAY && weekOfYear % 2 == 0
    }

    @Override
    LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return payDate.minusWeeks(2)
    }
}
