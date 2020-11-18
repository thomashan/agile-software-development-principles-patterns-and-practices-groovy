package io.github.thomashan.command

import io.github.thomashan.payroll.PayrollDatabase

trait Command {
    final PayrollDatabase payrollDatabase = io.github.thomashan.payroll.InMemPayrollDatabase.instance

    abstract void execute()
}
