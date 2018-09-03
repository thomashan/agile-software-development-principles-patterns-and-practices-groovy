package thomashan.github.io.payroll

import groovy.transform.builder.Builder
import thomashan.github.io.payroll.command.CommandBus
import thomashan.github.io.payroll.command.CommandBusFlowImpl
import thomashan.github.io.payroll.transaction.add.AddCommissionedEmployee

@Builder()
class PayrollApplication {
    DataSource dataSource
    Ui ui
    CommandBus commandBus

    static void main(String[] args) {
        PayrollApplication payrollApplication = PayrollApplication
                .builder()
                .dataSource(DataSource.flatFile)
                .ui(Ui.console)
                .commandBus(new CommandBusFlowImpl())
                .build()

        Thread.start {
            sleep(1000)
            println("adding transactions")
            payrollApplication.commandBus.push(new AddCommissionedEmployee(1, "Commissioned", "Address1", 1000, 20))
            println("added transactions")
            payrollApplication.commandBus.end()
        }

        println("getting transactions")
        payrollApplication
                .run()
    }

    void run() {
        commandBus.start()
    }
}
