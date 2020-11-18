package io.github.thomashan.payroll.command

import hu.akarnokd.reactive4javaflow.Folyam
import hu.akarnokd.reactive4javaflow.processors.CachingProcessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import io.github.thomashan.payroll.transaction.Command

@Singleton
class CommandBusFlowImpl implements CommandBus<Folyam<Command>> {
    private static final Logger logger = LoggerFactory.getLogger(CommandBusFlowImpl.class)
    private final CachingProcessor<Command> commands = new CachingProcessor()

    @Override
    void push(Command command) {
        logger.info("received: ${command}")
        commands.onNext(command)
    }

    /**
     * It doesn't matter when start() is invoked because it will replay all the transaction pushed into the command bus.
     */
    @Override
    void start() {
        logger.info("starting")
        commands.subscribe({ Command command -> command.execute() })
    }

    @Override
    void end() {
        logger.info("end")
        commands.onComplete()
    }

    @Override
    boolean isComplete() {
        return commands.hasComplete()
    }

    @Override
    Folyam<Command> getPublisher() {
        return commands
    }
}
