package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.method.DirectMethod
import io.github.thomashan.payroll.method.PaymentMethod

class ChangeDirect extends ChangeMethod {
    final String bank
    final String account

    ChangeDirect(int employeeId, String bank, String account) {
        super(employeeId)
        this.bank = bank
        this.account = account
    }

    @Override
    PaymentMethod getPaymentMethod() {
        return new DirectMethod(bank, account)
    }
}
