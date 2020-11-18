package thomashan.github.io.payroll.transaction.update

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.method.DirectMethod
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

class ChangeDirectCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "change direct transaction should change payment method to direct payment"() {
        String bank = "AnonBank"
        String account = "AnonAccount"
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        new ChangeDirectCommand(employeeId, bank, account).execute()
        DirectMethod directMethod = payrollDatabase.getEmployee(employeeId).paymentMethod

        assert directMethod.bank == bank
        assert directMethod.account == account
    }
}
