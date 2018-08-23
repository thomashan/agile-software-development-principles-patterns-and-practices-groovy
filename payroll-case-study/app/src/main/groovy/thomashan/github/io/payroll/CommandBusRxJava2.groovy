package thomashan.github.io.payroll

import io.reactivex.functions.Consumer
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import thomashan.github.io.payroll.transaction.Transaction

class CommandBusRxJava2 implements CommandBus {
    final FlowableProcessor<Transaction> transactions = PublishProcessor.create()

    @Override
    void push(Transaction transaction) {
        transactions.onNext(transaction)
    }

    @Override
    void pull() {
        transactions.blockingSubscribe(new Consumer<Transaction>() {
            @Override
            void accept(Transaction transaction) throws Exception {
                println("got ${transaction}")
                transaction.execute()
            }
        })
    }

    @Override
    void end() {
        transactions.onComplete()
    }
}
