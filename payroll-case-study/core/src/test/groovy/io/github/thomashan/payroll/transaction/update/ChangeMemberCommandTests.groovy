package io.github.thomashan.payroll.transaction.update


import org.junit.jupiter.api.Test

class ChangeMemberCommandTests implements ChangeEmployeeCommandTests {
    @Test
    void "change member transaction should execute without error"() {
        int memberId = 1
        double dues = 10
        new io.github.thomashan.payroll.transaction.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeMemberCommand(employeeId, memberId, dues).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        io.github.thomashan.payroll.affiliation.UnionAffiliation unionAffiliation = employee.affiliation.get()

        assert unionAffiliation.dues == dues
        assert payrollDatabase.getUnionMember(memberId) == employee
    }
}
