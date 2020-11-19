package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.method.MailMethod
import io.github.thomashan.payroll.method.PaymentMethod

class ChangeMail extends ChangeMethod {
    final String address

    ChangeMail(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    PaymentMethod getPaymentMethod() {
        return new MailMethod(address)
    }
}
