package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.method.PaymentMethod

abstract class ChangeMethod extends ChangeEmployee {
    ChangeMethod(int employeeId) {
        super(employeeId)
    }

    @Override
    void change(Employee employee) {
        employee.paymentMethod = paymentMethod
    }

    abstract protected PaymentMethod getPaymentMethod()
}
