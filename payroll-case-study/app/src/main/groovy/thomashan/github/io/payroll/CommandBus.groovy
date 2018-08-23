package thomashan.github.io.payroll

import thomashan.github.io.payroll.transaction.Transaction

trait CommandBus {
    abstract void push(Transaction transaction)

    abstract void pull()

    abstract void end()
}
