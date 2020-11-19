package io.github.thomashan.command

import io.github.thomashan.payroll.PayrollDatabaseInMemory
import io.github.thomashan.payroll.PayrollDatabase

trait Command {
    final PayrollDatabase payrollDatabase = PayrollDatabaseInMemory.instance

    abstract void execute()
}
