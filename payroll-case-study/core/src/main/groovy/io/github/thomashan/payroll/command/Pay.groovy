package io.github.thomashan.payroll.command

import io.github.thomashan.command.Command
import io.github.thomashan.payroll.Employee
import io.github.thomashan.payroll.PayCheque

import java.time.LocalDate

class Pay implements Command {
    final LocalDate payDate
    final Map<Integer, PayCheque> payCheques = [:]

    Pay(LocalDate payDate) {
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
