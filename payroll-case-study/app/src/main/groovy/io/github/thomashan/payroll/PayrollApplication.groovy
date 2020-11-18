package io.github.thomashan.payroll

import groovy.transform.builder.Builder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Builder()
class PayrollApplication {
    private static final Logger logger = LoggerFactory.getLogger(PayrollApplication.class)
    DataSource dataSource
    Ui ui
    io.github.thomashan.payroll.command.CommandBus commandBus

    static void main(String[] args) {
        PayrollApplication payrollApplication = builder()
                .dataSource(DataSource.FLAT_FILE)
                .ui(Ui.CONSOLE)
                .commandBus(io.github.thomashan.payroll.command.CommandBusFlowImpl.instance)
                .build()

        logger.info("adding transactions")
        payrollApplication.commandBus.push(new io.github.thomashan.payroll.transaction.add.AddCommissionedEmployee(1, "Commissioned", "Address1", 1000, 20))
        logger.info("added transactions")
        payrollApplication.commandBus.end()

        logger.info("getting transactions")
        payrollApplication
                .run()
    }

    void run() {
        commandBus.start()
    }
}
