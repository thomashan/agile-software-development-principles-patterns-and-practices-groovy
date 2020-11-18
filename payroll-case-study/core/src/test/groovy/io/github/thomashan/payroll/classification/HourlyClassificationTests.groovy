package io.github.thomashan.payroll.classification


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class HourlyClassificationTests {
    private LocalDate today = LocalDate.now()
    private HourlyClassification hourlyClassification
    private io.github.thomashan.payroll.TimeCard timeCard

    @BeforeEach
    void setUp() {
        hourlyClassification = new HourlyClassification(100)
        timeCard = new io.github.thomashan.payroll.TimeCard(today, 8)
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
        io.github.thomashan.payroll.PayCheque payCheque = new io.github.thomashan.payroll.PayCheque(today.minusWeeks(1), today)

        assert hourlyClassification.calculatePay(payCheque) == 0
    }

    @Test
    void "calculatePay with overtime card should return correct amount"() {
        hourlyClassification.addTimeCard(new io.github.thomashan.payroll.TimeCard(today, 9))
        io.github.thomashan.payroll.PayCheque payCheque = new io.github.thomashan.payroll.PayCheque(today.minusWeeks(1), today)

        assert hourlyClassification.calculatePay(payCheque) == 9.5 * hourlyClassification.hourlyRate
    }

    @Test
    void "calculatePay time card spanning multiple pay periods should return correct amount"() {
        hourlyClassification.addTimeCard(new io.github.thomashan.payroll.TimeCard(today.minusDays(7), 8))
        hourlyClassification.addTimeCard(new io.github.thomashan.payroll.TimeCard(today.minusDays(6), 8))
        hourlyClassification.addTimeCard(timeCard)
        hourlyClassification.addTimeCard(new io.github.thomashan.payroll.TimeCard(today.plusDays(1), 8))
        io.github.thomashan.payroll.PayCheque payCheque = new io.github.thomashan.payroll.PayCheque(today.minusWeeks(1), today)
        double grossPay = 16 * hourlyClassification.hourlyRate

        assert hourlyClassification.calculatePay(payCheque) == grossPay
    }
}
