package thomashan.github.io.payroll.transaction.update

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

class ChangeAddressTransactionTests implements ChangeEmployeeTransactionTests {
    @Test
    void "employee should be able to change their address"() {
        String address = "NewAnonAddress"
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeAddressTransaction(employeeId, address).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.address == address
    }
}
