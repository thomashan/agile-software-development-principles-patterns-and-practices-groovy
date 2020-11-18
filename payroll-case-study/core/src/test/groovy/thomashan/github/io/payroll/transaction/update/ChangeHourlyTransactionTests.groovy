package thomashan.github.io.payroll.transaction.update

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.schedule.WeeklySchedule
import thomashan.github.io.payroll.transaction.add.AddHourlyEmployee
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

class ChangeHourlyTransactionTests implements ChangeEmployeeTransactionTests {
    private double newHourlyRate = 100

    @Test
    void "non-hourly employee should be able to change hourly employee"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1000).execute()
        new ChangeHourlyTransaction(employeeId, newHourlyRate).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert ((HourlyClassification) employee.paymentClassification).hourlyRate == newHourlyRate
        assert employee.paymentSchedule == WeeklySchedule.instance
    }

    @Test
    void "hourly employee should be able to change hourly employee with updated hourly rate"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeHourlyTransaction(employeeId, newHourlyRate).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)

        assert ((HourlyClassification) employee.paymentClassification).hourlyRate == newHourlyRate
        assert employee.paymentSchedule == WeeklySchedule.instance
    }
}
