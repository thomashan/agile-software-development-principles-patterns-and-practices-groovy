package thomashan.github.io.payroll.transaction.update

import thomashan.github.io.payroll.classification.PaymentClassification
import thomashan.github.io.payroll.classification.SalariedClassification
import thomashan.github.io.payroll.schedule.MonthlySchedule
import thomashan.github.io.payroll.schedule.PaymentSchedule

class ChangeSalariedCommand extends ChangeClassificationCommand {
    final double salary

    ChangeSalariedCommand(int employeeId, double salary) {
        super(employeeId)
        this.salary = salary
    }

    @Override
    PaymentClassification getPaymentClassification() {
        return new SalariedClassification(salary)
    }

    @Override
    PaymentSchedule getPaymentSchedule() {
        return MonthlySchedule.instance
    }
}
