package thomashan.github.io.payroll.command

import thomashan.github.io.payroll.transaction.Command

import java.util.concurrent.Flow.Publisher

trait CommandBus<T extends Publisher<Command>> {
    abstract void push(Command transaction)

    /**
     * Depending on the implementation starting maybe synchronous or asynchronous.
     */
    abstract void start()

    abstract void end()

    abstract boolean isComplete()

    abstract T getPublisher()
}
