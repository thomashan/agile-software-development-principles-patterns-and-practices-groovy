package thomashan.github.io.payroll.transaction.add

import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.method.HoldMethod
import thomashan.github.io.payroll.schedule.PaymentSchedule
import thomashan.github.io.payroll.transaction.Transaction

@TupleConstructor
trait AddEmployeeTransaction implements Transaction {
    final int employeeId
    final String name
    final String address
    final PaymentClassification paymentClassification
    final PaymentSchedule paymentSchedule

    @Override
    void execute() {
        Employee employee = new Employee(employeeId, name, address, paymentClassification, paymentSchedule, HoldMethod.instance)
        payrollDatabase.addEmployee(employee)
    }
}
