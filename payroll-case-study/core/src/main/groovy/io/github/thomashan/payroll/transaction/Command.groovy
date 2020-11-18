package io.github.thomashan.payroll.transaction

trait Command {
    final io.github.thomashan.payroll.PayrollDatabase payrollDatabase = io.github.thomashan.payroll.InMemPayrollDatabase.instance

    abstract void execute()
}
