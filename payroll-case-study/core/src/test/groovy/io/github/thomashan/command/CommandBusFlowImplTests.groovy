package io.github.thomashan.command


import org.awaitility.Awaitility
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommandBusFlowImplTests {
    private io.github.thomashan.payroll.transaction.Command command

    @BeforeEach
    void setUp() {
        command = new io.github.thomashan.payroll.transaction.add.AddCommissionedEmployee(1, "Commissioned", "Address1", 1000, 20)
    }

    @Test
    void "test CommandBusFlowImpl"() {
        CommandBus commandBus = CommandBusFlowImpl.instance
        commandBus.start()

        commandBus.push(command)
        commandBus.end()

        Awaitility.await().until { commandBus.complete }

        commandBus.publisher
                .test()
                .assertComplete()
    }
}
