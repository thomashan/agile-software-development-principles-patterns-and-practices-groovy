package io.github.thomashan.payroll.command.add

import groovy.transform.TupleConstructor
import io.github.thomashan.command.Command
import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.classification.PaymentClassification
import io.github.thomashan.payroll.method.HoldMethod
import io.github.thomashan.payroll.schedule.PaymentSchedule

@TupleConstructor
trait AddEmployeeCommand implements Command {
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
