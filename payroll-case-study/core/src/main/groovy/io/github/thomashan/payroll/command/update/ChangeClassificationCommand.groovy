package io.github.thomashan.payroll.command.update


import io.github.thomashan.payroll.classification.PaymentClassification

abstract class ChangeClassificationCommand extends ChangeEmployeeCommand {
    ChangeClassificationCommand(int employeeId) {
        super(employeeId)
    }

    @Override
    void change(io.github.thomashan.payroll.Employee employee) {
        employee.paymentClassification = paymentClassification
        employee.paymentSchedule = paymentSchedule
    }

    abstract protected PaymentClassification getPaymentClassification()

    abstract protected io.github.thomashan.payroll.schedule.PaymentSchedule getPaymentSchedule()
}
