package io.github.thomashan.payroll.command.update


import org.junit.jupiter.api.Test

class ChangeNameTests implements ChangeEmployeeTests {
    @Test
    void "employee should be able to change their name"() {
        String name = "NewAnonName"
        new io.github.thomashan.payroll.command.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeName(employeeId, name).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.name == name
    }
}
