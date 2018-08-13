package thomashan.github.io.payroll.transaction

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase
import thomashan.github.io.payroll.TimeCard
import thomashan.github.io.payroll.classification.HourlyClassification

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertThrows

class TimeCardTransactionTests {
    private PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    private int employeeId = 1
    private LocalDate today = LocalDate.now()

    @AfterEach
    void tearDown() {
        try {
            payrollDatabase.deleteEmployee(employeeId)
        } catch (ex) {

        }
    }

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
