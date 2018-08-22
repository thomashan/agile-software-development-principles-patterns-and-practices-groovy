package thomashan.github.io.payroll.classification

import thomashan.github.io.payroll.PayCheque
import thomashan.github.io.payroll.SalesReceipt

class CommissionedClassification implements PaymentClassification {
    private final List<SalesReceipt> salesReceipts = []
    final double salary
    final double commissionRate

    CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary
        this.commissionRate = commissionRate
    }

    void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceipts.add(salesReceipt)
    }

    @Override
    double calculatePay(PayCheque payCheque) {
        return salesReceipts.collect { it.amount * commissionRate / 100 }.sum() + salary
    }
}
