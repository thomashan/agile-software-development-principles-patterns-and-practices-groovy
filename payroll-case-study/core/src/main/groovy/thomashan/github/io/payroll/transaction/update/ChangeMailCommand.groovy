package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.method.MailMethod
import thomashan.github.io.payroll.method.PaymentMethod

class ChangeMailCommand extends ChangeMethodCommand {
    final String address

    ChangeMailCommand(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    PaymentMethod getPaymentMethod() {
        return new MailMethod(address)
    }
}
