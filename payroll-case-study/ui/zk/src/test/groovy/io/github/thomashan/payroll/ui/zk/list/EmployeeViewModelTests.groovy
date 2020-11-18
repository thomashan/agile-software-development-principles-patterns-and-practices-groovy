package io.github.thomashan.payroll.ui.zk.list

import io.github.thomashan.payroll.classification.PaymentClassification
import org.junit.jupiter.api.Test

class EmployeeViewModelTests implements io.github.thomashan.payroll.ui.EmployeeManagement {
    @Test
    void "hourly employee wage should show correctly"() {
        new io.github.thomashan.payroll.command.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 1).execute()
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "1.0 / hour"
    }

    @Test
    void "salaried employee wage should show correctly"() {
        new io.github.thomashan.payroll.command.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", 1).execute()
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "1.0 / month"
    }

    @Test
    void "commissioned employee wage should show correctly"() {
        new io.github.thomashan.payroll.command.add.AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1, 15).execute()
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "1.0 / month + 15.0% sales"
    }

    @Test
    void "unknown classification returns error"() {
        new io.github.thomashan.payroll.command.add.AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", 1, 15).execute()
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)
        employee.paymentClassification = new UnknownPaymentClassification()
        EmployeeViewModel employeeViewModel = new EmployeeViewModel(employee)

        assert employeeViewModel.waging == "Couldn't determine waging"
    }

    private static class UnknownPaymentClassification implements PaymentClassification {
        @Override
        double calculatePay(io.github.thomashan.payroll.PayCheque payCheque) {
            return 0
        }
    }
}
