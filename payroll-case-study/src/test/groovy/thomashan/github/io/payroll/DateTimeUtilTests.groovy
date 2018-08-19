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
}
