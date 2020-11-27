package io.github.thomashan.payroll.command.add

import io.github.thomashan.command.Command
import io.github.thomashan.command.CommandTests
import io.github.thomashan.payroll.classification.HourlyClassification
import io.github.thomashan.payroll.method.HoldMethod
import io.github.thomashan.payroll.schedule.WeeklySchedule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddHourlyEmployeeTests implements CommandTests {
    private Command command
    private String name = "Hourly"
    private String address = "HourlyHome"
    private double hourlyRate = 100.0

    @BeforeEach
    void setUp() {
        command = new AddHourlyEmployee(employeeId, name, address, hourlyRate)
    }

    @Test
    void "add hourly employee should return correct employee name"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).name == name
    }

    @Test
    void "add hourly employee should return correct employee address"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).address == address
    }

    @Test
    void "add hourly employee should return correct payment classification"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentClassification instanceof HourlyClassification
    }

    @Test
    void "add hourly employee should return correct hourlyRate"() {
        command.execute()
        HourlyClassification hourlyClassification = (HourlyClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert hourlyClassification.hourlyRate == hourlyRate
    }

    @Test
    void "add hourly employee should return correct payment schedule"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentSchedule instanceof WeeklySchedule
    }

    @Test
    void "add hourly employee should return correct payment method"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentMethod instanceof HoldMethod
    }
}