package thomashan.github.io.payroll.transaction.delete

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.transaction.CommandTests
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

import static org.junit.jupiter.api.Assertions.assertThrows

class DeleteEmployeeTests implements CommandTests {
    @BeforeEach
    void setUp() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1).execute()
    }

    @Test
    void "delete existing employee should execute without error"() {
        new DeleteEmployee(employeeId).execute()

        assert payrollDatabase.getEmployee(employeeId) == null
    }

    @Test
    void "delete non-existing employee should throw an error"() {
        assertThrows(RuntimeException, { new DeleteEmployee(2).execute() })
    }
}
