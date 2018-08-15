package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee

class ChangeAddressTransactionTests implements TransactionTests {
    @Test
    void "employee should be able to change their address"() {
        String address = "NewAnonAddress"
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeAddressTransaction(employeeId, address).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.address == address
    }
}
