package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.schedule.PaymentSchedule

abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
    ChangeClassificationTransaction(int employeeId) {
        super(employeeId)
    }

    @Override
    void change(Employee employee) {
        employee.paymentClassification = paymentClassification
        employee.paymentSchedule = paymentSchedule
    }

    abstract protected PaymentClassification getPaymentClassification()

    abstract protected PaymentSchedule getPaymentSchedule()
}
