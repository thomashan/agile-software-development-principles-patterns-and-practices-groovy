package thomashan.github.io.payroll.transaction.add

import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.schedule.PaymentSchedule
import thomashan.github.io.payroll.transaction.Transaction

@TupleConstructor
abstract class AddEmployeeTransaction implements Transaction {
    final int employeeId
    final String name
    final String address

    abstract PaymentClassification getPaymentClassification()

    abstract PaymentSchedule getPaymentSchedule()

    @Override
    void execute() {
        Employee employee = new Employee(employeeId, name, address, paymentClassification, paymentSchedule, new HoldMethod())
        payrollDatabase.addEmployee(employee)
    }
}
