package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import org.junit.jupiter.api.Test

class ChangeNameTests implements ChangeEmployeeTests {
    @Test
    void "employee should be able to change their name"() {
        String name = "NewAnonName"
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeName(employeeId, name).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.name == name
    }
}
