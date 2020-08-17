package thomashan.github.io.payroll.transaction.add

import groovy.transform.Canonical
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.classification.SalariedClassification
import thomashan.github.io.payroll.schedule.MonthlySchedule
import thomashan.github.io.payroll.schedule.PaymentSchedule

@Canonical
class AddSalariedEmployee extends AddEmployeeTransaction {
    final double monthlySalary

    AddSalariedEmployee(int employeeId, String name, String address, double monthlySalary) {
        super(employeeId, name, address)
        this.monthlySalary = monthlySalary
    }

    @Override
    PaymentClassification getPaymentClassification() {
        return new SalariedClassification(monthlySalary)
    }

    @Override
    PaymentSchedule getPaymentSchedule() {
        return new MonthlySchedule()
    }
}
