package io.github.thomashan.payroll.event.add

import groovy.transform.Canonical
import io.github.thomashan.payroll.classification.CommissionedClassification
import io.github.thomashan.payroll.schedule.BiweeklySchedule

@Canonical
class CommissionedEmployeeCreated implements EmployeeCreated {
    final double monthlySalary
    final double commissionRate

    CommissionedEmployeeCreated(int employeeId, String name, String address, double monthlySalary, double commissionRate) {
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__employeeId = employeeId
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__name = name
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__address = address
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__paymentClassification = new CommissionedClassification(monthlySalary, commissionRate)
        this.io_github_thomashan_payroll_event_add_EmployeeCreated__paymentSchedule = BiweeklySchedule.instance
        this.monthlySalary = monthlySalary
        this.commissionRate = commissionRate
    }
}
