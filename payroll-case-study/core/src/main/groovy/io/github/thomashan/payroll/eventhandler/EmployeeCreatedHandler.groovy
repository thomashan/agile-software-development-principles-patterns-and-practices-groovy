package io.github.thomashan.payroll.eventhandler

import io.github.thomashan.event.EventHandler
import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.event.add.EmployeeCreated
import io.github.thomashan.payroll.method.HoldMethod

class EmployeeCreatedHandler implements EventHandler<EmployeeCreated, Employee> {
    @Override
    Optional<Employee> plus(EmployeeCreated event) {
        return Optional.of(new Employee(
                event.employeeId,
                event.name,
                event.address,
                event.paymentClassification,
                event.paymentSchedule,
                HoldMethod.instance))
    }
}
