package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.TimeCard
import thomashan.github.io.payroll.classification.HourlyClassification

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class TimeCardTransactionTests implements TransactionTests {
    private LocalDate today = LocalDate.now()

    @Test
    void "time card transaction on nonexistent employee should throw an error"() {
        assertThrows(RuntimeException, { new TimeCardTransaction(employeeId, today, 8).execute() })
    }

    @Test
    void "time card transaction on hourly employee should execute without error"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new TimeCardTransaction(employeeId, today, 8).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        TimeCard timeCard = ((HourlyClassification) employee.paymentClassification).getTimeCard(today)

        assert timeCard.hours == 8
    }

    @Test
    void "time card transaction on salaried employee should throw an error"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()

        assertThrows(RuntimeException, { new TimeCardTransaction(employeeId, today, 8).execute() })
    }

    @Test
    void "time card transaction on commissioned employee should throw an error"() {
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1000, 20).execute()

        assertThrows(RuntimeException, { new TimeCardTransaction(employeeId, today, 8).execute() })
    }
}
