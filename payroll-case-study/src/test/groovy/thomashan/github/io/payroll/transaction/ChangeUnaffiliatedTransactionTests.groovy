package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee

class ChangeUnaffiliatedTransactionTests implements TransactionTests {
    @Test
    void "change unaffiliated transaction should execute without error"() {
        int memberId = 1
        double dues = 10
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeMemberTransaction(employeeId, memberId, dues).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)

        new ChangeUnaffiliatedTransaction(employeeId).execute()


        assert payrollDatabase.getUnionMember(memberId) == null
    }
}
