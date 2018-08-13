package thomashan.github.io.payroll.classification

import jdk.nashorn.internal.ir.annotations.Immutable
import thomashan.github.io.payroll.TimeCard

import java.time.LocalDate

@Immutable
class HourlyClassification extends PaymentClassification {
    private Map<LocalDate, TimeCard> timeCards = [:]
    double hourlyRate

    HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate
    }

    void addTimeCard(TimeCard timeCard) {
        timeCards.put(timeCard.date, timeCard)
    }

    TimeCard getTimeCard(LocalDate date) {
        if (!timeCards.containsKey(date)) {
            throw new RuntimeException("Time card doesn't exist for ${date}")
        }

        return timeCards.get(date)
    }
}
