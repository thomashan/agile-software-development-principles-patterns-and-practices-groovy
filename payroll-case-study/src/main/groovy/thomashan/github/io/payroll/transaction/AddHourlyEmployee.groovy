package thomashan.github.io.payroll.transaction

import groovy.transform.Canonical
import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.schedule.PaymentSchedule
import thomashan.github.io.payroll.schedule.WeeklySchedule

@Canonical
class AddHourlyEmployee extends AddEmployeeTransaction {
    final double hourlyRate

    AddHourlyEmployee(int employeeId, String name, String address, double hourlyRate) {
        super(employeeId, name, address)
        this.hourlyRate = hourlyRate
    }

    @Override
    PaymentClassification getPaymentClassification() {
        return new HourlyClassification(hourlyRate)
    }

    @Override
    PaymentSchedule getPaymentSchedule() {
        return new WeeklySchedule()
    }
}
