package io.github.thomashan.payroll.command

import io.github.thomashan.command.CommandTests
import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.affiliation.UnionAffiliation
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class AddServiceChargeTests implements CommandTests {
    private int memberId = 1
    private LocalDate today = LocalDate.now()
    private double charge = 10

    @BeforeEach
    void setUp() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 10).execute()
    }

    @Test
    void "add service charge command on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new AddServiceCharge(2, today, charge).execute() })
    }

    @Test
    void "add service charge command on an existing employee with union affiliation should execute without error"() {
        Employee employee = payrollDatabase.getEmployee(employeeId)
        payrollDatabase.addUnionMember(memberId, employee)
        employee.affiliation = Optional.of(new UnionAffiliation(memberId, 10))
        new AddServiceCharge(employeeId, today, charge).execute()

        assert ((UnionAffiliation) employee.affiliation.get()).getServiceCharge(today).amount == charge
    }
}
