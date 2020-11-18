package io.github.thomashan.payroll.command.add

import io.github.thomashan.command.Command
import io.github.thomashan.command.CommandTests
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddSalariedEmployeeTests implements CommandTests {
    private Command command
    private String name = "Salaried"
    private String address = "SalariedHome"
    private double salary = 1000.0

    @BeforeEach
    void setUp() {
        command = new AddSalariedEmployee(employeeId, name, address, salary)
    }

    @Test
    void "add salaried employee should return correct employee name"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).name == name
    }

    @Test
    void "add salaried employee should return correct employee address"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).address == address
    }

    @Test
    void "add salaried employee should return correct payment classification"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentClassification instanceof io.github.thomashan.payroll.classification.SalariedClassification
    }

    @Test
    void "add salaried employee should return correct salary"() {
        command.execute()
        io.github.thomashan.payroll.classification.SalariedClassification salariedClassification = (io.github.thomashan.payroll.classification.SalariedClassification) payrollDatabase.getEmployee(employeeId).paymentClassification

        assert salariedClassification.salary == salary
    }

    @Test
    void "add salaried employee should return correct payment schedule"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentSchedule instanceof io.github.thomashan.payroll.schedule.MonthlySchedule
    }

    @Test
    void "add salaried employee should return correct payment method"() {
        command.execute()

        assert payrollDatabase.getEmployee(employeeId).paymentMethod instanceof io.github.thomashan.payroll.method.HoldMethod
    }
}
