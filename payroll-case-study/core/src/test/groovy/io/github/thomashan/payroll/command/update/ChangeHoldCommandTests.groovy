package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import io.github.thomashan.payroll.method.HoldMethod
import org.junit.jupiter.api.Test

class ChangeHoldCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "change hold command should change payment method to hold payment"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        new ChangeHoldCommand(employeeId).execute()
        HoldMethod holdMethod = payrollDatabase.getEmployee(employeeId).paymentMethod

        assert holdMethod
    }
}
