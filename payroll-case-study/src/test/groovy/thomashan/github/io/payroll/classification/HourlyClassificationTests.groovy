package thomashan.github.io.payroll.classification

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.TimeCard

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class HourlyClassificationTests {
    private LocalDate today = LocalDate.now()
    private HourlyClassification hourlyClassification
    private TimeCard timeCard

    @BeforeEach
    void setUp() {
        hourlyClassification = new HourlyClassification(8)
        timeCard = new TimeCard(today, 8)
        hourlyClassification.addTimeCard(timeCard)
    }

    @Test
    void "getTimeCard on existing date should return a time card"() {
        assert hourlyClassification.getTimeCard(today) == timeCard
    }

    @Test
    void "getTimeCard on nonexistent date should throw an error"() {
        assertThrows(RuntimeException, { hourlyClassification.getTimeCard(today.plusDays(1)) })
    }
}
