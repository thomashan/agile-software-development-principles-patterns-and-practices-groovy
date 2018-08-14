package thomashan.github.io.payroll

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.affiliation.NoAffiliation
import thomashan.github.io.payroll.affiliation.UnionAffiliation
import thomashan.github.io.payroll.transaction.AddHourlyEmployee

import static org.junit.jupiter.api.Assertions.assertThrows

class InMemPayrollDatabaseTests {
    private PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    private int memberId = 1
    private Employee employee

    @BeforeEach
    void setUp() {
        int employeeId = 1
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 10).execute()

        employee = payrollDatabase.getEmployee(employeeId)
    }

    @Test
    void "addUnionMember should throw exception if employee has no affiliation"() {
        assertThrows(RuntimeException, { payrollDatabase.addUnionMember(memberId, employee) })
    }

    @Test
    void "addUnionMember should throw exception if employee is not affiliated with union"() {
        employee.affiliation = Optional.of(new NoAffiliation())

        assertThrows(RuntimeException, { payrollDatabase.addUnionMember(memberId, employee) })
    }

    @Test
    void "addUnionMember should add to union if employee is affiliated with union"() {
        employee.affiliation = Optional.of(new UnionAffiliation())

        payrollDatabase.addUnionMember(memberId, employee)
    }
}
