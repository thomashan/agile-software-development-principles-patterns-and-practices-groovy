package thomashan.github.io.payroll.command

import thomashan.github.io.payroll.transaction.Transaction

import java.util.concurrent.Flow.Publisher

trait CommandBus<T extends Publisher<Transaction>> {
    abstract void push(Transaction transaction)

    /**
     * Depending on the implementation starting maybe synchronous or asynchronous.
     */
    abstract void start()

    abstract void end()

    abstract boolean isComplete()

    abstract T getPublisher()
}
