package io.github.thomashan.command

import io.github.thomashan.payroll.InMemPayrollDatabase
import io.github.thomashan.payroll.PayrollDatabase

trait Command {
    final PayrollDatabase payrollDatabase = InMemPayrollDatabase.instance

    abstract void execute()
}
