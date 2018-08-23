package thomashan.github.io.payroll

import org.reactivestreams.Publisher
import thomashan.github.io.payroll.transaction.Transaction

trait TransactionSource {
    final Publisher<Transaction> transactions
}
