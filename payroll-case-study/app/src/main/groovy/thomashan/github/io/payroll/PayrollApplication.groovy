package thomashan.github.io.payroll

import groovy.transform.builder.Builder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import thomashan.github.io.payroll.command.CommandBus
import thomashan.github.io.payroll.command.CommandBusFlowImpl
import thomashan.github.io.payroll.transaction.add.AddCommissionedEmployee

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

        logger.info("adding transactions")
        payrollApplication.commandBus.push(new AddCommissionedEmployee(1, "Commissioned", "Address1", 1000, 20))
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
