package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import org.junit.jupiter.api.Test

class ChangeAddressTests implements ChangeEmployeeTests {
    @Test
    void "employee should be able to change their address"() {
        String address = "NewAnonAddress"
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeAddress(employeeId, address).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.address == address
    }
}
