package thomashan.github.io.payroll.schedule

import org.junit.jupiter.api.Test

import java.time.LocalDate

class WeeklyScheduleTests {
    private WeeklySchedule weeklySchedule = new WeeklySchedule()

    @Test
    void "isPayDate should return true if it is friday"() {
        assert weeklySchedule.isPayDate(new LocalDate(2018, 8, 17))
    }

    @Test
    void "isPayDate should return false if it is not friday"() {
        assert !weeklySchedule.isPayDate(new LocalDate(2018, 8, 18))
    }
}
