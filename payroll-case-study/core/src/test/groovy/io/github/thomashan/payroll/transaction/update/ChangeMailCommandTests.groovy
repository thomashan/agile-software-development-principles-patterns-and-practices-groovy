package io.github.thomashan.payroll.transaction.update


import org.junit.jupiter.api.Test

class ChangeMailCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "change mail transaction should change payment method to mail payment"() {
        String address = "AnonAddress"
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        new ChangeMailCommand(employeeId, address).execute()
        io.github.thomashan.payroll.method.MailMethod mailMethod = payrollDatabase.getEmployee(employeeId).paymentMethod

        assert mailMethod.address == address
    }
}
