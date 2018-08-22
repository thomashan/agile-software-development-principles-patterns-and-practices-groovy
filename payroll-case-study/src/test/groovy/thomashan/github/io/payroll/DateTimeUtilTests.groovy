package thomashan.github.io.payroll

import org.junit.jupiter.api.Test

import java.time.LocalDate

class DateTimeUtilTests {
    private LocalDate today = LocalDate.of(2018, 8, 17)

    @Test
    void "numberOfFridaysInPayPeriod should return 0 if start and end is the same"() {
        assert DateTimeUtil.numberOfFridaysInPayPeriod(today, today) == 0
    }

    @Test
    void "numberOfFridaysInPayPeriod should return correct"() {
        assert DateTimeUtil.numberOfFridaysInPayPeriod(today, today.plusWeeks(1)) == 1
    }

    @Test
    void "daysInMonth should return correct value"() {
        assert DateTimeUtil.daysInMonth(LocalDate.of(1999, 2, 1)) == 28
    }

    @Test
    void "daysInMonth should return correct leap year value"() {
        assert DateTimeUtil.daysInMonth(LocalDate.of(2000, 2, 1)) == 29
    }
}
