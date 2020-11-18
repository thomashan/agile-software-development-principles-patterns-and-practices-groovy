package io.github.thomashan.payroll.command.update


import org.junit.jupiter.api.Test

class ChangeMemberTests implements ChangeEmployeeTests {
    @Test
    void "change member command should execute without error"() {
        int memberId = 1
        double dues = 10
        new io.github.thomashan.payroll.command.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeMember(employeeId, memberId, dues).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        io.github.thomashan.payroll.affiliation.UnionAffiliation unionAffiliation = employee.affiliation.get()

        assert unionAffiliation.dues == dues
        assert payrollDatabase.getUnionMember(memberId) == employee
    }
}
