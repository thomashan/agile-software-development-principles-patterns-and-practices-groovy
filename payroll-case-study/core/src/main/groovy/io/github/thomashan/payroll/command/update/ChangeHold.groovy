package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.method.HoldMethod
import io.github.thomashan.payroll.method.PaymentMethod

class ChangeHold extends ChangeMethod {
    ChangeHold(int employeeId) {
        super(employeeId)
    }

    @Override
    PaymentMethod getPaymentMethod() {
        return HoldMethod.instance
    }
}
