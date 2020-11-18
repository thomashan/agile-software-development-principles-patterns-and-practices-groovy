package io.github.thomashan.payroll.command.update

class ChangeDirect extends ChangeMethod {
    final String bank
    final String account

    ChangeDirect(int employeeId, String bank, String account) {
        super(employeeId)
        this.bank = bank
        this.account = account
    }

    @Override
    io.github.thomashan.payroll.method.PaymentMethod getPaymentMethod() {
        return new io.github.thomashan.payroll.method.DirectMethod(bank, account)
    }
}
