package thomashan.github.io.payroll.schedule

import org.junit.jupiter.api.Test

import java.time.LocalDate

class BiweeklyScheduleTests {
    private BiweeklySchedule biweeklySchedule = BiweeklySchedule.instance
    private LocalDate today = LocalDate.now()

    @Test
    void "isPayDate should return true if it is friday on second week"() {
        assert biweeklySchedule.isPayDate(new LocalDate(2018, 1, 12))
    }

    @Test
    void "isPayDate should return false if it is not friday on first week"() {
        assert !biweeklySchedule.isPayDate(new LocalDate(2018, 1, 5))
    }

    @Test
    void "isPayDate should return false if it is not friday"() {
        assert !biweeklySchedule.isPayDate(new LocalDate(2018, 1, 10))
    }

    @Test
    void "getPayPeriodStartDate should return two weeks before payDate"() {
        assert biweeklySchedule.getPayPeriodStartDate(today) == today.minusWeeks(2)
    }
}
