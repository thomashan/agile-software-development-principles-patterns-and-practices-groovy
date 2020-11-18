package io.github.thomashan.payroll

import org.junit.jupiter.api.Test

import java.time.LocalDate

class DateTimeUtilTests {
    private LocalDate thursday = LocalDate.of(2018, 8, 16)
    private LocalDate friday = LocalDate.of(2018, 8, 17)

    @Test
    void "numberOfFridaysInPayPeriod should return 0 if start and end is the same thursday"() {
        assert DateTimeUtil.numberOfFridaysInPayPeriod(thursday, thursday) == 0
    }

    @Test
    void "numberOfFridaysInPayPeriod should return 0 if start and end is the same friday"() {
        assert DateTimeUtil.numberOfFridaysInPayPeriod(friday, friday) == 0
    }

    @Test
    void "numberOfFridaysInPayPeriod should return 0 if start day is Friday and end is next day"() {
        assert DateTimeUtil.numberOfFridaysInPayPeriod(friday, friday.plusDays(1)) == 0
    }

    @Test
    void "numberOfFridaysInPayPeriod should return 0 if start day is thursday and end is friday"() {
        assert DateTimeUtil.numberOfFridaysInPayPeriod(thursday, friday) == 1
    }

    @Test
    void "numberOfFridaysInPayPeriod should return correct if stared on thursday and finished next thursday"() {
        assert DateTimeUtil.numberOfFridaysInPayPeriod(thursday, thursday.plusWeeks(1)) == 1
    }

    @Test
    void "numberOfFridaysInPayPeriod should return correct if started on friday and finished next friday"() {
        assert DateTimeUtil.numberOfFridaysInPayPeriod(friday, friday.plusWeeks(1)) == 1
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
