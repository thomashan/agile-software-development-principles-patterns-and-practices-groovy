package io.github.thomashan.payroll.command.update

class ChangeMailCommand extends ChangeMethodCommand {
    final String address

    ChangeMailCommand(int employeeId, String address) {
        super(employeeId)
        this.address = address
    }

    @Override
    io.github.thomashan.payroll.method.PaymentMethod getPaymentMethod() {
        return new io.github.thomashan.payroll.method.MailMethod(address)
    }
}
