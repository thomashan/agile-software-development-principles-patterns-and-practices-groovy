package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.method.PaymentMethod

class ChangeHoldCommand extends ChangeMethodCommand {
    ChangeHoldCommand(int employeeId) {
        super(employeeId)
    }

    @Override
    PaymentMethod getPaymentMethod() {
        return HoldMethod.instance
    }
}
