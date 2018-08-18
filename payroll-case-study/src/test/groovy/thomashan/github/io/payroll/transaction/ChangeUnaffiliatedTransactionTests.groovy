package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.Test

class ChangeUnaffiliatedTransactionTests implements ChangeEmployeeTransactionTests {
    @Test
    void "change unaffiliated transaction should execute without error"() {
        int memberId = 1
        double dues = 10
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeMemberTransaction(employeeId, memberId, dues).execute()

        new ChangeUnaffiliatedTransaction(employeeId).execute()

        assert payrollDatabase.getUnionMember(memberId) == null
    }
}
