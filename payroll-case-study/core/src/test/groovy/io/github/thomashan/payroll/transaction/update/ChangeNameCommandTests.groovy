package io.github.thomashan.payroll.transaction.update


import org.junit.jupiter.api.Test

class ChangeNameCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "employee should be able to change their name"() {
        String name = "NewAnonName"
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeNameCommand(employeeId, name).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.name == name
    }
}
