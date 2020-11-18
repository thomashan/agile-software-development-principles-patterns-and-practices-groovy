package io.github.thomashan.payroll.transaction.update


import org.junit.jupiter.api.Test

class ChangeHoldCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "change hold transaction should change payment method to hold payment"() {
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        new ChangeHoldCommand(employeeId).execute()
        io.github.thomashan.payroll.method.HoldMethod holdMethod = payrollDatabase.getEmployee(employeeId).paymentMethod

        assert holdMethod
    }
}
