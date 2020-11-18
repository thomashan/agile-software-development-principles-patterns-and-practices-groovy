package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.method.PaymentMethod

class ChangeHoldTransaction extends ChangeMethodTransaction {
    ChangeHoldTransaction(int employeeId) {
        super(employeeId)
    }

    @Override
    PaymentMethod getPaymentMethod() {
        return HoldMethod.instance
    }
}
