package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.classification.PaymentClassification
import io.github.thomashan.payroll.schedule.PaymentSchedule

abstract class ChangeClassification extends ChangeEmployee {
    ChangeClassification(int employeeId) {
        super(employeeId)
    }

    @Override
    void change(Employee employee) {
        employee.paymentClassification = paymentClassification
        employee.paymentSchedule = paymentSchedule
    }

    abstract protected PaymentClassification getPaymentClassification()

    abstract protected PaymentSchedule getPaymentSchedule()
}
