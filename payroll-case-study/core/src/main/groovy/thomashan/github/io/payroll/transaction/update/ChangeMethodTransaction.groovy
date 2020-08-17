package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.method.PaymentMethod

abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
    ChangeMethodTransaction(int employeeId) {
        super(employeeId)
    }

    @Override
    void change(Employee employee) {
        employee.paymentMethod = paymentMethod
    }

    abstract protected PaymentMethod getPaymentMethod()
}
