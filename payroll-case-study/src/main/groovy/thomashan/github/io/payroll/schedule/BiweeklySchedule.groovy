package thomashan.github.io.payroll.schedule

import groovy.transform.Immutable

import java.time.LocalDate
import java.time.temporal.WeekFields

import static java.time.DayOfWeek.FRIDAY

@Immutable
class BiweeklySchedule implements PaymentSchedule {
    @Override
    boolean isPayDate(LocalDate payDate) {
        int weekOfYear = payDate.get(WeekFields.of(Locale.default).weekOfWeekBasedYear())
        return payDate.dayOfWeek == FRIDAY && weekOfYear % 2 == 0
    }
}
