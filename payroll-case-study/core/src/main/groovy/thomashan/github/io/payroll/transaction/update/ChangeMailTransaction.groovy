package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.method.MailMethod
import thomashan.github.io.payroll.method.PaymentMethod

class ChangeMailTransaction extends ChangeMethodTransaction {
    final String address

    ChangeMailTransaction(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    PaymentMethod getPaymentMethod() {
        return new MailMethod(address)
    }
}
