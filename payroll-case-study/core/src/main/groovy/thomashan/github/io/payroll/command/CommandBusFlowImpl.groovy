package thomashan.github.io.payroll.command

import hu.akarnokd.reactive4javaflow.Folyam
import hu.akarnokd.reactive4javaflow.processors.CachingProcessor
import thomashan.github.io.payroll.transaction.Transaction

@Singleton
class CommandBusFlowImpl implements CommandBus<Folyam<Transaction>> {
    private final CachingProcessor<Transaction> transactions = new CachingProcessor()


    @Override
    void push(Transaction transaction) {
        println("received: ${transaction}")
        transactions.onNext(transaction)
    }

    /**
     * It doesn't matter when start() is invoked because it will replay all the transaction pushed into the command bus.
     */
    @Override
    void start() {
        println("starting")
        transactions.subscribe({ it.execute() })
    }

    @Override
    void end() {
        println("end")
        transactions.onComplete()
    }

    @Override
    boolean isComplete() {
        return transactions.hasComplete()
    }

    @Override
    Folyam<Transaction> getPublisher() {
        return transactions
    }
}
