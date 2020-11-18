package io.github.thomashan.payroll.command

import io.github.thomashan.command.CommandTests
import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.TimeCard
import io.github.thomashan.payroll.command.add.AddCommissionedEmployee
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class TimeCardCommandTests implements CommandTests {
    private LocalDate today = LocalDate.now()

    @Test
    void "time card command on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new TimeCardCommand(employeeId, today, 8).execute() })
    }

    @Test
    void "time card command on hourly employee should execute without error"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new TimeCardCommand(employeeId, today, 8).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        TimeCard timeCard = ((io.github.thomashan.payroll.classification.HourlyClassification) employee.paymentClassification).getTimeCard(today)

        assert timeCard.hours == 8
    }

    @Test
    void "time card command on salaried employee should throw an error"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new TimeCardCommand(employeeId, today, 8).execute() })
    }

    @Test
    void "time card command on commissioned employee should throw an error"() {
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1000, 20).execute()

        assertThrows(RuntimeException, { new TimeCardCommand(employeeId, today, 8).execute() })
    }
}
