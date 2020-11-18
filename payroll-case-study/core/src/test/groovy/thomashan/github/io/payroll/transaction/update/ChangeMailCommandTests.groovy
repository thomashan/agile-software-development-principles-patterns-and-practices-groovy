package thomashan.github.io.payroll.transaction.update

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.method.MailMethod
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

class ChangeMailCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "change mail transaction should change payment method to mail payment"() {
        String address = "AnonAddress"
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        new ChangeMailCommand(employeeId, address).execute()
        MailMethod mailMethod = payrollDatabase.getEmployee(employeeId).paymentMethod

        assert mailMethod.address == address
    }
}
