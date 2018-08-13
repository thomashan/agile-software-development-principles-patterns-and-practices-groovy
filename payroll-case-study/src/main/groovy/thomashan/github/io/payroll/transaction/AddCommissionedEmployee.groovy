package thomashan.github.io.payroll.transaction

import groovy.transform.Canonical
import thomashan.github.io.payroll.classification.CommissionedClassification
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.schedule.BiweeklySchedule
import thomashan.github.io.payroll.schedule.PaymentSchedule

@Canonical
class AddCommissionedEmployee extends AddEmployeeTransaction {
    final double monthlySalary
    final double commissionRate

    AddCommissionedEmployee(int employeeId, String name, String address, double monthlySalary, double commissionRate) {
        super(employeeId, name, address)
        this.monthlySalary = monthlySalary
        this.commissionRate = commissionRate
    }

    @Override
    PaymentClassification getPaymentClassification() {
        return new CommissionedClassification(monthlySalary, commissionRate)
    }

    @Override
    PaymentSchedule getPaymentSchedule() {
        return new BiweeklySchedule()
    }
}
