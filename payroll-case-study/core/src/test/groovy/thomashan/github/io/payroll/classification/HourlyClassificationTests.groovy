package thomashan.github.io.payroll.classification

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.PayCheque
import thomashan.github.io.payroll.TimeCard

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class HourlyClassificationTests {
    private LocalDate today = LocalDate.now()
    private HourlyClassification hourlyClassification
    private TimeCard timeCard

    @BeforeEach
    void setUp() {
        hourlyClassification = new HourlyClassification(100)
        timeCard = new TimeCard(today, 8)
    }

    @Test
    void "getTimeCard on existing date should return a time card"() {
        hourlyClassification.addTimeCard(timeCard)
        assert hourlyClassification.getTimeCard(today) == timeCard
    }

    @Test
    void "getTimeCard on nonexistent date should throw an error"() {
        assertThrows(RuntimeException, { hourlyClassification.getTimeCard(today.plusDays(1)) })
    }

    @Test
    void "calculatePay with no time card should return 0"() {
        PayCheque payCheque = new PayCheque(today.minusWeeks(1), today)

        assert hourlyClassification.calculatePay(payCheque) == 0
    }

    @Test
    void "calculatePay with overtime card should return correct amount"() {
        hourlyClassification.addTimeCard(new TimeCard(today, 9))
        PayCheque payCheque = new PayCheque(today.minusWeeks(1), today)

        assert hourlyClassification.calculatePay(payCheque) == 9.5 * hourlyClassification.hourlyRate
    }

    @Test
    void "calculatePay time card spanning multiple pay periods should return correct amount"() {
        hourlyClassification.addTimeCard(new TimeCard(today.minusDays(7), 8))
        hourlyClassification.addTimeCard(new TimeCard(today.minusDays(6), 8))
        hourlyClassification.addTimeCard(timeCard)
        hourlyClassification.addTimeCard(new TimeCard(today.plusDays(1), 8))
        PayCheque payCheque = new PayCheque(today.minusWeeks(1), today)
        double grossPay = 16 * hourlyClassification.hourlyRate

        assert hourlyClassification.calculatePay(payCheque) == grossPay
    }
}
