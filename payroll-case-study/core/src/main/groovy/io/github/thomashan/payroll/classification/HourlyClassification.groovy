package io.github.thomashan.payroll.classification

import groovy.transform.Immutable
import groovy.transform.TupleConstructor

import java.time.LocalDate

@Immutable
@TupleConstructor
class HourlyClassification implements PaymentClassification {
    private final Map<LocalDate, io.github.thomashan.payroll.TimeCard> timeCards = [:]
    double hourlyRate

    void addTimeCard(io.github.thomashan.payroll.TimeCard timeCard) {
        timeCards.put(timeCard.date, timeCard)
    }

    io.github.thomashan.payroll.TimeCard getTimeCard(LocalDate date) {
        if (!timeCards.containsKey(date)) {
            throw new RuntimeException("Time card doesn't exist for ${date}")
        }

        return timeCards.get(date)
    }

    @Override
    double calculatePay(io.github.thomashan.payroll.PayCheque payCheque) {
        timeCards.findAll {
            it.key > payCheque.payPeriodStartDate && it.key <= payCheque.payPeriodEndDate
        }
                .collect { calculatePayForTimeCard(it.value) }
                .sum() ?: 0
    }

    private double calculatePayForTimeCard(io.github.thomashan.payroll.TimeCard timeCard) {
        double overtimeHours = Math.max(0, timeCard.hours - 8)
        double normalHours = timeCard.hours - overtimeHours

        return hourlyRate * normalHours + 1.5 * hourlyRate * overtimeHours
    }
}
