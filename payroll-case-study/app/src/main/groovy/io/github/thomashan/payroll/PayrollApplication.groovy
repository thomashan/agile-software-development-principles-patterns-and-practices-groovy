package io.github.thomashan.payroll

import groovy.transform.builder.Builder
import io.github.thomashan.command.CommandBus
import io.github.thomashan.command.CommandBusFlowImpl
import io.github.thomashan.payroll.command.add.AddCommissionedEmployee
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Builder()
class PayrollApplication {
    private static final Logger logger = LoggerFactory.getLogger(PayrollApplication.class)
    DataSource dataSource
    Ui ui
    CommandBus commandBus

    static void main(String[] args) {
        PayrollApplication payrollApplication = builder()
                .dataSource(DataSource.FLAT_FILE)
                .ui(Ui.CONSOLE)
                .commandBus(CommandBusFlowImpl.instance)
                .build()

        logger.info("adding command")
        payrollApplication.commandBus.push(new AddCommissionedEmployee(1, "Commissioned", "Address1", 1000, 20))
        logger.info("added command")
        payrollApplication.commandBus.end()

        logger.info("getting command")
        payrollApplication
                .run()
    }

    void run() {
        commandBus.start()
    }
}
