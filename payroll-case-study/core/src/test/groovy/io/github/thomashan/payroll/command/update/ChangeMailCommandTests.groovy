package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import io.github.thomashan.payroll.method.MailMethod
import org.junit.jupiter.api.Test

class ChangeMailCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "change mail command should change payment method to mail payment"() {
        String address = "AnonAddress"
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        new ChangeMailCommand(employeeId, address).execute()
        MailMethod mailMethod = payrollDatabase.getEmployee(employeeId).paymentMethod

        assert mailMethod.address == address
    }
}
