package thomashan.github.io.payroll.classification

import groovy.transform.Immutable
import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.PayCheque
import thomashan.github.io.payroll.TimeCard

import java.time.LocalDate

@Immutable
@TupleConstructor
class HourlyClassification implements PaymentClassification {
    private Map<LocalDate, TimeCard> timeCards = [:]
    double hourlyRate

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
