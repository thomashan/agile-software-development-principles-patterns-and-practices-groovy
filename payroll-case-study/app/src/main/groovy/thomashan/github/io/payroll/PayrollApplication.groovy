package thomashan.github.io.payroll

import groovy.transform.builder.Builder
import io.reactivex.functions.Consumer

@Builder()
class PayrollApplication {
    DataSource dataSource
    Ui ui
    TransactionSource transactionSource

    static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            void accept(String string) throws Exception {
                println(string)
            }
        }
        TransactionSource transactionSource = new TextParserTransactionSource<String>(consumer)

        PayrollApplication payrollApplication = PayrollApplication
                .builder()
                .dataSource(DataSource.flatFile)
                .ui(Ui.console)
                .transactionSource(transactionSource)
                .build()

        payrollApplication
                .run()
    }

    void run() {
        transactionSource.getTransactions()
    }
}
