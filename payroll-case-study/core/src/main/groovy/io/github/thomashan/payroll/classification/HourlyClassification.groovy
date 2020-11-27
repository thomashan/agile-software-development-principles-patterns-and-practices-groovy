package io.github.thomashan.payroll.classification

import groovy.transform.EqualsAndHashCode
import groovy.transform.Immutable
import groovy.transform.TupleConstructor
import io.github.thomashan.payroll.PayCheque
import io.github.thomashan.payroll.TimeCard

import java.time.LocalDate

@Immutable
@TupleConstructor
@EqualsAndHashCode
class HourlyClassification implements PaymentClassification {
    private final Map<LocalDate, TimeCard> timeCards = [:]
    final double hourlyRate

    void addTimeCard(TimeCard timeCard) {
        timeCards.put(timeCard.date, timeCard)
    }

    TimeCard getTimeCard(LocalDate date) {
        if (!timeCards.containsKey(date)) {
            throw new RuntimeException("Time card doesn't exist for ${date}")
        }

        return timeCards.get(date)
    }

    @Override
    double calculatePay(PayCheque payCheque) {
        timeCards.findAll {
            it.key > payCheque.payPeriodStartDate && it.key <= payCheque.payPeriodEndDate
        }
                .collect { calculatePayForTimeCard(it.value) }
                .sum() ?: 0
    }

    private double calculatePayForTimeCard(TimeCard timeCard) {
        double overtimeHours = Math.max(0, timeCard.hours - 8)
        double normalHours = timeCard.hours - overtimeHours

        return hourlyRate * normalHours + 1.5 * hourlyRate * overtimeHours
    }
}