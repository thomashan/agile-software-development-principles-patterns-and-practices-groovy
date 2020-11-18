package io.github.thomashan.payroll.transaction.update

class ChangeDirectCommand extends ChangeMethodCommand {
    final String bank
    final String account

    ChangeDirectCommand(int employeeId, String bank, String account) {
        super(employeeId)
        this.bank = bank
        this.account = account
    }

    @Override
    io.github.thomashan.payroll.method.PaymentMethod getPaymentMethod() {
        return new io.github.thomashan.payroll.method.DirectMethod(bank, account)
    }
}
