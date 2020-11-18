package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.method.DirectMethod
import thomashan.github.io.payroll.method.PaymentMethod

class ChangeDirectCommand extends ChangeMethodCommand {
    final String bank
    final String account

    ChangeDirectCommand(int employeeId, String bank, String account) {
        super(employeeId)
        this.bank = bank
        this.account = account
    }

    @Override
    PaymentMethod getPaymentMethod() {
        return new DirectMethod(bank, account)
    }
}
