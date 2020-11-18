package thomashan.github.io.payroll.command

import hu.akarnokd.reactive4javaflow.Folyam
import hu.akarnokd.reactive4javaflow.processors.CachingProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thomashan.github.io.payroll.transaction.Command

@Singleton
class CommandBusFlowImpl implements CommandBus<Folyam<Command>> {
    private static final Logger logger = LoggerFactory.getLogger(CommandBusFlowImpl.class)
    private final CachingProcessor<Command> transactions = new CachingProcessor()

    @Override
    void push(Command command) {
        logger.info("received: ${command}")
        transactions.onNext(command)
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
    Folyam<Command> getPublisher() {
        return transactions
    }
}
