package thomashan.github.io.payroll.ui.zk.list

import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.classification.CommissionedClassification
import thomashan.github.io.payroll.classification.HourlyClassification
import thomashan.github.io.payroll.classification.SalariedClassification

@Canonical
@TupleConstructor
class EmployeeViewModel {
    final int employeeId
    final String name
    final String address
    final String waging

    EmployeeViewModel(Employee employee) {
        this.employeeId = employee.employeeId
        this.name = employee.name
        this.address = employee.address
        this.waging = createWaging(employee)
    }

    private String createWaging(Employee employee) {
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
