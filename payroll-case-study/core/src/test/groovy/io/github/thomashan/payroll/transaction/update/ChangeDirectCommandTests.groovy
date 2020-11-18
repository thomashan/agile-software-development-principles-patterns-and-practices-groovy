package io.github.thomashan.payroll.transaction.update


import org.junit.jupiter.api.Test

class ChangeDirectCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "change direct transaction should change payment method to direct payment"() {
        String bank = "AnonBank"
        String account = "AnonAccount"
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        new ChangeDirectCommand(employeeId, bank, account).execute()
        io.github.thomashan.payroll.method.DirectMethod directMethod = payrollDatabase.getEmployee(employeeId).paymentMethod

        assert directMethod.bank == bank
        assert directMethod.account == account
    }
}
