package io.github.thomashan.payroll.transaction


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class ServiceChargeCommandTests implements CommandTests {
    private int memberId = 1
    private LocalDate today = LocalDate.now()
    private double charge = 10

    @BeforeEach
    void setUp() {
        new io.github.thomashan.payroll.transaction.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 10).execute()
    }

    @Test
    void "add service charge transaction on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new ServiceChargeCommand(2, today, charge).execute() })
    }

    @Test
    void "add service charge transaction on an existing employee with union affiliation should execute without error"() {
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        payrollDatabase.addUnionMember(memberId, employee)
        employee.affiliation = Optional.of(new io.github.thomashan.payroll.affiliation.UnionAffiliation(memberId, 10))
        new ServiceChargeCommand(employeeId, today, charge).execute()

        assert ((io.github.thomashan.payroll.affiliation.UnionAffiliation) employee.affiliation.get()).getServiceCharge(today).amount == charge
    }
}
