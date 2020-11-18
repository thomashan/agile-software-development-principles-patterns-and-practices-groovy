package io.github.thomashan.payroll.transaction


import java.time.LocalDate

class PaydayCommand implements Command {
    final LocalDate payDate
    final Map<Integer, io.github.thomashan.payroll.PayCheque> payCheques = [:]

    PaydayCommand(LocalDate payDate) {
        this.payDate = payDate
    }

    @Override
    void execute() {
        for (Integer employeeId : payrollDatabase.allEmployeeIds) {
            io.github.thomashan.payroll.Employee employee = payrollDatabase.getEmployee(employeeId)

            if (employee.isPayDate(payDate)) {
                io.github.thomashan.payroll.PayCheque payCheque = new io.github.thomashan.payroll.PayCheque(employee.getPayPeriodStartDate(payDate), payDate)
                payCheques.put(employeeId, payCheque)
                employee.payday(payCheque)
            }
        }
    }

    Optional<io.github.thomashan.payroll.PayCheque> getPayCheque(int employeeId) {
        return Optional.ofNullable(payCheques.get(employeeId))
    }
}
