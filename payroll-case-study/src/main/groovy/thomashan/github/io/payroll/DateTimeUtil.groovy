package thomashan.github.io.payroll

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

import static java.time.DayOfWeek.FRIDAY

final class DateTimeUtil {
    private DateTimeUtil() {}

    static long numberOfFridaysInPayPeriod(LocalDate startDate, LocalDate endDate) {
        LocalDate firstFriday = startDate.with(TemporalAdjusters.next(FRIDAY))
        LocalDate lastFriday = getLastFriday(endDate)

        return ChronoUnit.WEEKS.between(firstFriday, lastFriday) + 1
    }

    private static getLastFriday(LocalDate endDate) {
        if (DayOfWeek.from(endDate) == FRIDAY) {
            return endDate
        } else {
            return endDate.with(TemporalAdjusters.previous(FRIDAY))
        }
    }
}
