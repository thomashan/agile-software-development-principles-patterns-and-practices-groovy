package io.github.thomashan.payroll.command.update

class ChangeMail extends ChangeMethod {
    final String address

    ChangeMail(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    io.github.thomashan.payroll.method.PaymentMethod getPaymentMethod() {
        return new io.github.thomashan.payroll.method.MailMethod(address)
    }
}
