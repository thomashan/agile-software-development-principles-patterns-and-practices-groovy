package io.github.thomashan.payroll.command.add

import groovy.transform.TupleConstructor
import io.github.thomashan.command.Command
import io.github.thomashan.payroll.classification.PaymentClassification

@TupleConstructor
trait AddEmployeeCommand implements Command {
    final int employeeId
    final String name
    final String address
    final PaymentClassification paymentClassification
    final io.github.thomashan.payroll.schedule.PaymentSchedule paymentSchedule

    @Override
    void execute() {
        io.github.thomashan.payroll.Employee employee = new io.github.thomashan.payroll.Employee(employeeId, name, address, paymentClassification, paymentSchedule, io.github.thomashan.payroll.method.HoldMethod.instance)
        payrollDatabase.addEmployee(employee)
    }
}
