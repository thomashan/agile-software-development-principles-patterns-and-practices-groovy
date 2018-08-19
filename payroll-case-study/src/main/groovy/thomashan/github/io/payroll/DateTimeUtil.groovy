package thomashan.github.io.payroll

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

import static java.time.DayOfWeek.FRIDAY

final class DateTimeUtil {
    private DateTimeUtil() {}

    static long numberOfFridaysInPayPeriod(LocalDate startDate, LocalDate endDate) {
        LocalDate firstFriday = startDate.with(TemporalAdjusters.next(FRIDAY))
        LocalDate lastFriday = endDate.with(TemporalAdjusters.previous(FRIDAY))

        return ChronoUnit.WEEKS.between(firstFriday, lastFriday) + 1
    }
}
