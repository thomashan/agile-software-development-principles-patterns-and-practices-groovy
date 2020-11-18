package io.github.thomashan.payroll.command.update

abstract class ChangeMethod extends ChangeEmployee {
    ChangeMethod(int employeeId) {
        super(employeeId)
    }

    @Override
    void change(io.github.thomashan.payroll.Employee employee) {
        employee.paymentMethod = paymentMethod
    }

    abstract protected io.github.thomashan.payroll.method.PaymentMethod getPaymentMethod()
}
