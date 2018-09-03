package thomashan.github.io.payroll.command

import thomashan.github.io.payroll.transaction.Transaction

trait CommandBus {
    abstract void push(Transaction transaction)

    /**
     * Depending on the implementation starting maybe synchronous or asynchronous.
     */
    abstract void start()

    abstract void end()

    abstract boolean isComplete()
}
