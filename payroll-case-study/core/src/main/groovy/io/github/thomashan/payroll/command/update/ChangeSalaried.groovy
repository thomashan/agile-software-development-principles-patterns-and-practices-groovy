package io.github.thomashan.payroll.command.update


import io.github.thomashan.payroll.classification.PaymentClassification
import io.github.thomashan.payroll.classification.SalariedClassification

class ChangeSalaried extends ChangeClassification {
    final double salary

    ChangeSalaried(int employeeId, double salary) {
        super(employeeId)
        this.salary = salary
    }

    @Override
    PaymentClassification getPaymentClassification() {
        return new SalariedClassification(salary)
    }

    @Override
    io.github.thomashan.payroll.schedule.PaymentSchedule getPaymentSchedule() {
        return io.github.thomashan.payroll.schedule.MonthlySchedule.instance
    }
}
