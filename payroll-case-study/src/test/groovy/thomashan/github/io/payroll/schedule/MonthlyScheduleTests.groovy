package thomashan.github.io.payroll.schedule

import org.junit.jupiter.api.Test

import java.time.LocalDate

class MonthlyScheduleTests {
    private MonthlySchedule monthlySchedule = new MonthlySchedule()

    @Test
    void "isPayDate should return true if it is the last day of the month"() {
        assert monthlySchedule.isPayDate(new LocalDate(2001, 11, 30))
    }

    @Test
    void "isPayDate should return false if it is not the last day of the month"() {
        assert !monthlySchedule.isPayDate(new LocalDate(2001, 11, 29))
    }
}
