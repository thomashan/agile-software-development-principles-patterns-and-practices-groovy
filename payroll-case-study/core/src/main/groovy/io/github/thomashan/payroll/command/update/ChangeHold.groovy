package io.github.thomashan.payroll.command.update

class ChangeHold extends ChangeMethod {
    ChangeHold(int employeeId) {
        super(employeeId)
    }

    @Override
    io.github.thomashan.payroll.method.PaymentMethod getPaymentMethod() {
        return io.github.thomashan.payroll.method.HoldMethod.instance
    }
}
