package io.github.thomashan.payroll.event.add

import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.classification.CommissionedClassification
import io.github.thomashan.payroll.classification.HourlyClassification
import io.github.thomashan.payroll.classification.SalariedClassification
import io.github.thomashan.payroll.method.HoldMethod
import io.github.thomashan.payroll.schedule.BiweeklySchedule
import io.github.thomashan.payroll.schedule.MonthlySchedule
import io.github.thomashan.payroll.schedule.WeeklySchedule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EmployeeCreatedHandlerTests {
    private static final int employeeId = 1
    private static final String employeeName = "anonName"
    private static final String employeeAddress = "anonAddress"
    private static final double salary = 1
    private static final double commissionRate = 1
    private static final double hourlyRate = 1
    private EmployeeCreatedHandler employeeCreatedHandler

    @BeforeEach
    void setUp() {
        this.employeeCreatedHandler = new EmployeeCreatedHandler()
    }

    @Test
    void "handler creates new commissioned employee from CommissionedEmployeeCreated event"() {
        Employee employee = (employeeCreatedHandler + new CommissionedEmployeeCreated(employeeId, employeeName, employeeAddress, salary, commissionRate)).get()
        CommissionedClassification commissionedClassification = new CommissionedClassification(salary, commissionRate)

        assert employeeId == employee.employeeId
        assert employeeName == employee.name
        assert employeeAddress == employee.address
        assert commissionedClassification == employee.paymentClassification
        assert BiweeklySchedule.instance == employee.paymentSchedule
        assert HoldMethod.instance == employee.paymentMethod
    }

    @Test
    void "handler creates hourly employee from HourlyEmployeeCreated event"() {
        Employee employee = (employeeCreatedHandler + new HourlyEmployeeCreated(employeeId, employeeName, employeeAddress, hourlyRate)).get()
        HourlyClassification hourlyClassification = new HourlyClassification(hourlyRate)

        assert employeeId == employee.employeeId
        assert employeeName == employee.name
        assert employeeAddress == employee.address
        assert hourlyClassification == employee.paymentClassification
        assert WeeklySchedule.instance == employee.paymentSchedule
        assert HoldMethod.instance == employee.paymentMethod
    }

    @Test
    void "handler creates salaried employee from SalariedEmployeeCreated event"() {
        Employee employee = (employeeCreatedHandler + new SalariedEmployeeCreated(employeeId, employeeName, employeeAddress, salary)).get()
        SalariedClassification salariedClassification = new SalariedClassification(salary)

        assert employeeId == employee.employeeId
        assert employeeName == employee.name
        assert employeeAddress == employee.address
        assert salariedClassification == employee.paymentClassification
        assert MonthlySchedule.instance == employee.paymentSchedule
        assert HoldMethod.instance == employee.paymentMethod
    }
}
