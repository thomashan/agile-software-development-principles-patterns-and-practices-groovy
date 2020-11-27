package io.github.thomashan.payroll.command.update

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.affiliation.UnionAffiliation
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import org.junit.jupiter.api.Test

class ChangeMemberTests implements ChangeEmployeeTests {
    @Test
    void "change member command should execute without error"() {
        int memberId = 1
        double dues = 10
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeMember(employeeId, memberId, dues).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        UnionAffiliation unionAffiliation = employee.affiliation.get()

        assert unionAffiliation.dues == dues
        assert payrollDatabase.getUnionMember(memberId) == employee
    }
}
