package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.affiliation.UnionAffiliation

class ChangeMemberTransactionTests implements TransactionTests {
    @Test
    void "change member transaction should execute without error"() {
        int memberId = 1
        double dues = 10
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeMemberTransaction(employeeId, memberId, dues).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        UnionAffiliation unionAffiliation = employee.affiliation.get()

        assert unionAffiliation.dues == dues
        assert payrollDatabase.getUnionMember(memberId) == employee
    }
}
