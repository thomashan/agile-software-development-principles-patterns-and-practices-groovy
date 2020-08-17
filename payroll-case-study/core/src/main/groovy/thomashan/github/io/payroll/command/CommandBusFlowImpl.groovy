package thomashan.github.io.payroll.command

import hu.akarnokd.reactive4javaflow.Folyam
import hu.akarnokd.reactive4javaflow.processors.CachingProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thomashan.github.io.payroll.transaction.Transaction

@Singleton
class CommandBusFlowImpl implements CommandBus<Folyam<Transaction>> {
    private static final Logger logger = LoggerFactory.getLogger(CommandBusFlowImpl.class)
    private final CachingProcessor<Transaction> transactions = new CachingProcessor()

    @Override
    void push(Transaction transaction) {
        logger.info("received: ${transaction}")
        transactions.onNext(transaction)
    }

    /**
     * It doesn't matter when start() is invoked because it will replay all the transaction pushed into the command bus.
     */
    @Override
    void start() {
        logger.info("starting")
        transactions.subscribe({ it.execute() })
    }

    @Override
    void end() {
        logger.info("end")
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
