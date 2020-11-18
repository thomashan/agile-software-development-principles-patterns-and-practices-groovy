package io.github.thomashan.payroll.command

import io.github.thomashan.command.Command
import io.github.thomashan.payroll.classification.CommissionedClassification

import java.time.LocalDate

class SaleReceiptCommand implements Command {
    int employeeId
    LocalDate date
    double amount

    SaleReceiptCommand(int employeeId, LocalDate date, double amount) {
        this.employeeId = employeeId
        this.date = date
        this.amount = amount
    }

    @Override
    void execute() {
        io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)

        if (employee) {
            if (employee.paymentClassification instanceof CommissionedClassification) {
                CommissionedClassification commissionedClassification = employee.paymentClassification
                commissionedClassification.addSalesReceipt(new io.github.thomashan.payroll.SalesReceipt(date, amount))
            } else {
                throw new RuntimeException("Error adding sales receipt to non-commissioned employee")
            }
        } else {
            throw new RuntimeException("No such employee")
        }
    }
}
