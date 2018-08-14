package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.InMemPayrollDatabase
import thomashan.github.io.payroll.PayrollDatabase
import thomashan.github.io.payroll.SalesReceipt
import thomashan.github.io.payroll.classification.CommissionedClassification

import java.time.LocalDate

class SaleReceiptTransaction implements Transaction {
    private final PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance
    int employeeId
    LocalDate date
    double amount

    SaleReceiptTransaction(int employeeId, LocalDate date, double amount) {
        this.employeeId = employeeId
        this.date = date
        this.amount = amount
    }

    @Override
    void execute() {
        Employee employee = payrollDatabase.getEmployee(employeeId)

        if (employee) {
            if (employee.paymentClassification instanceof CommissionedClassification) {
                CommissionedClassification commissionedClassification = employee.paymentClassification
                commissionedClassification.addSalesReceipt(new SalesReceipt(date, amount))
            } else {
                throw new RuntimeException("Error adding sales receipt to non-commissioned employee")
            }
        } else {
            throw new RuntimeException("No such employee")
        }
    }
}
