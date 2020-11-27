package io.github.thomashan.payroll.schedule

import org.junit.jupiter.api.Test

import java.time.LocalDate

class MonthlyScheduleTests {
    private MonthlySchedule monthlySchedule = MonthlySchedule.instance
    private LocalDate today = LocalDate.now()

    @Test
    void "isPayDate should return true if it is the last day of the month"() {
        assert monthlySchedule.isPayDate(new LocalDate(2001, 11, 30))
    }

    @Test
    void "isPayDate should return false if it is not the last day of the month"() {
        assert !monthlySchedule.isPayDate(new LocalDate(2001, 11, 29))
    }

    @Test
    void "getPayPeriodStartDate should return month before payDate"() {
        assert monthlySchedule.getPayPeriodStartDate(today) == today.minusMonths(1)
    }
}
