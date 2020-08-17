package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.method.DirectMethod
import thomashan.github.io.payroll.method.PaymentMethod

class ChangeDirectTransaction extends ChangeMethodTransaction {
    final String bank
    final String account

    ChangeDirectTransaction(int employeeId, String bank, String account) {
        super(employeeId)
        this.bank = bank
        this.account = account
    }

    @Override
    PaymentMethod getPaymentMethod() {
        return new DirectMethod(bank, account)
    }
}
