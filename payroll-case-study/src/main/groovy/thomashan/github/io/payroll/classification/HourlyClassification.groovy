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
        return timeCards.findAll { it.key > payCheque.payPeriodStartDate && it.key <= payCheque.payPeriodEndDate }
                .collect { it.value.hours }
                .sum() * hourlyRate
    }
}
