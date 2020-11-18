package io.github.thomashan.payroll.transaction


import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class TimeCardCommandTests implements CommandTests {
    private LocalDate today = LocalDate.now()

    @Test
    void "time card transaction on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new TimeCardCommand(employeeId, today, 8).execute() })
    }

    @Test
    void "time card transaction on hourly employee should execute without error"() {
        new io.github.thomashan.payroll.transaction.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new TimeCardCommand(employeeId, today, 8).execute()

        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)

        io.github.thomashan.payroll.TimeCard timeCard = ((io.github.thomashan.payroll.classification.HourlyClassification) employee.paymentClassification).getTimeCard(today)

        assert timeCard.hours == 8
    }

    @Test
    void "time card transaction on salaried employee should throw an error"() {
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new TimeCardCommand(employeeId, today, 8).execute() })
    }

    @Test
    void "time card transaction on commissioned employee should throw an error"() {
        new io.github.thomashan.payroll.transaction.add.AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1000, 20).execute()

        assertThrows(RuntimeException, { new TimeCardCommand(employeeId, today, 8).execute() })
    }
}
