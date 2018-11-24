package thomashan.github.io.payroll.ui.zk.list

import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.PayCheque
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.transaction.add.AddCommissionedEmployee
import thomashan.github.io.payroll.transaction.add.AddHourlyEmployee
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee
import thomashan.github.io.payroll.ui.EmployeeManagement

class EmployeeViewModelTests implements EmployeeManagement {
    @Test
    void "hourly employee wage should show correctly"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 1).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "1.0 / hour"
    }

    @Test
    void "salaried employee wage should show correctly"() {
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "1.0 / month"
    }

    @Test
    void "commissioned employee wage should show correctly"() {
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1, 15).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "1.0 / month + 15.0% sales"
    }

    @Test
    void "unknown classification returns error"() {
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1, 15).execute()
        Employee employee = payrollDatabase.getEmployee(employeeId)
        employee.paymentClassification = new UnknownPaymentClassification()
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "Couldn't determine waging"
    }

    private static class UnknownPaymentClassification implements PaymentClassification {
        @Override
        double calculatePay(PayCheque payCheque) {
            return 0
        }
    }
}
