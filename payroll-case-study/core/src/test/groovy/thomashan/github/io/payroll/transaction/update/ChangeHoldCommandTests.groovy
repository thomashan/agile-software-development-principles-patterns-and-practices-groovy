package thomashan.github.io.payroll.transaction.update

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

class ChangeHoldCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "change hold transaction should change payment method to hold payment"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        new ChangeHoldCommand(employeeId).execute()
        HoldMethod holdMethod = payrollDatabase.getEmployee(employeeId).paymentMethod

        assert holdMethod
    }
}
