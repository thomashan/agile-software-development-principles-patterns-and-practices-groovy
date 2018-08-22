package thomashan.github.io.payroll.transaction

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.PayCheque

import java.time.LocalDate

class PaydayTransaction implements Transaction {
    final LocalDate payDate
    final Map<Integer, PayCheque> payCheques = [:]

    PaydayTransaction(LocalDate payDate) {
        this.payDate = payDate
    }

    @Override
    void execute() {
        for (Integer employeeId : payrollDatabase.allEmployeeIds) {
            Employee employee = payrollDatabase.getEmployee(employeeId)

            if (employee.isPayDate(payDate)) {
                PayCheque payCheque = new PayCheque(employee.getPayPeriodStartDate(payDate), payDate)
                payCheques.put(employeeId, payCheque)
                employee.payday(payCheque)
            }
        }
    }

    Optional<PayCheque> getPayCheque(int employeeId) {
        return Optional.ofNullable(payCheques.get(employeeId))
    }
}
