package io.github.thomashan.payroll.ui.zk.list

import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import io.github.thomashan.payroll.classification.CommissionedClassification
import io.github.thomashan.payroll.classification.HourlyClassification
import io.github.thomashan.payroll.classification.SalariedClassification

@Canonical
@TupleConstructor
class EmployeeViewModel {
    final int employeeId
    final String name
    final String address
    final String waging

    EmployeeViewModel(io.github.thomashan.payroll.Employee employee) {
        this.employeeId = employee.employeeId
        this.name = employee.name
        this.address = employee.address
        this.waging = createWaging(employee)
    }

    private String createWaging(io.github.thomashan.payroll.Employee employee) {
        switch (employee.paymentClassification) {
            case CommissionedClassification:
                CommissionedClassification commissionedClassification = (CommissionedClassification) employee.paymentClassification
                return "${commissionedClassification.salary} / month + ${commissionedClassification.commissionRate}% sales"
            case HourlyClassification:
                HourlyClassification hourlyClassification = (HourlyClassification) employee.paymentClassification
                return "${hourlyClassification.hourlyRate} / hour"
            case SalariedClassification:
                SalariedClassification salariedClassification = (SalariedClassification) employee.paymentClassification
                return "${salariedClassification.salary} / month"
            default:
                return "Couldn't determine waging"
        }
    }
}
