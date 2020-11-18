package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import io.github.thomashan.payroll.method.DirectMethod
import org.junit.jupiter.api.Test

class ChangeDirectTests implements ChangeEmployeeTests {
    @Test
    void "change direct command should change payment method to direct payment"() {
        String bank = "AnonBank"
        String account = "AnonAccount"
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        new ChangeDirect(employeeId, bank, account).execute()
        DirectMethod directMethod = payrollDatabase.getEmployee(employeeId).paymentMethod

        assert directMethod.bank == bank
        assert directMethod.account == account
    }
}
