package thomashan.github.io.payroll.transaction.update

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.classification.SalariedClassification
import thomashan.github.io.payroll.schedule.MonthlySchedule
import thomashan.github.io.payroll.transaction.add.AddHourlyEmployee
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee

class ChangeSalariedTransactionTests implements ChangeEmployeeTransactionTests {
    private double newSalary = 2000

    @Test
    void "non-salaried employee should be able to change salaried employee"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeSalariedTransaction(employeeId, newSalary).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        SalariedClassification salariedClassification = employee.paymentClassification

        assert salariedClassification.salary == newSalary
        assert employee.paymentSchedule == new MonthlySchedule()
    }

    @Test
    void "salaried employee should be able to change salaried employee with updated salary"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 100).execute()
        new ChangeSalariedTransaction(employeeId, newSalary).execute()

        Employee employee = payrollDatabase.getEmployee(employeeId)
        SalariedClassification salariedClassification = employee.paymentClassification

        assert salariedClassification.salary == newSalary
        assert employee.paymentSchedule == new MonthlySchedule()
    }
}
