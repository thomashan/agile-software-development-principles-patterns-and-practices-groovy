package io.github.thomashan.payroll.command.update


import org.junit.jupiter.api.Test

class ChangeAddressCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "employee should be able to change their address"() {
        String address = "NewAnonAddress"
        new io.github.thomashan.payroll.command.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeAddressCommand(employeeId, address).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.address == address
    }
}
