package thomashan.github.io.payroll

import org.reactivestreams.Publisher
import thomashan.github.io.payroll.transaction.Transaction

class TextParserTransactionSource implements TransactionSource {
    @Override
    Publisher<Transaction> getTransactions() {
        return null
    }
}
