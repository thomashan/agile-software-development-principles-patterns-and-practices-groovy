package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee

class ChangeNameTransactionTests implements TransactionTests {
    @Test
    void "employee should be able to change their name"() {
        String name = "NewAnonName"
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeNameTransaction(employeeId, name).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert employee.name == name
    }
}
