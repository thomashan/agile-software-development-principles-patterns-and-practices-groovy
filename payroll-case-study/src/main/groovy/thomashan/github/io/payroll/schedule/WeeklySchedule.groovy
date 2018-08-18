package thomashan.github.io.payroll.schedule

import groovy.transform.Immutable

import java.time.LocalDate

import static java.time.DayOfWeek.FRIDAY

@Immutable
class WeeklySchedule implements PaymentSchedule {
    @Override
    boolean isPayDate(LocalDate payDate) {
        return payDate.dayOfWeek == FRIDAY
    }
}
