package io.github.thomashan.payroll.event.add

import io.github.thomashan.event.Event
import io.github.thomashan.payroll.classification.PaymentClassification
import io.github.thomashan.payroll.schedule.PaymentSchedule

trait EmployeeCreated extends Event {
    final int employeeId
    final String name
    final String address
    final PaymentClassification paymentClassification
    final PaymentSchedule paymentSchedule
}
