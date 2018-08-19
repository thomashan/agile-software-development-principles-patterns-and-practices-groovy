package thomashan.github.io.payroll.classification

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.PayCheque
import thomashan.github.io.payroll.SalesReceipt

import java.time.LocalDate

class CommissionedClassificationTests {
    private LocalDate today = LocalDate.now()
    private CommissionedClassification commissionedClassification
    private SalesReceipt salesReceipt

    @BeforeEach
    void setUp() {
        commissionedClassification = new CommissionedClassification(1000, 20)
        salesReceipt = new SalesReceipt(today, 30)
    }

    @Test
    void "addSalesReceipt should execute without error"() {
        commissionedClassification.addSalesReceipt(salesReceipt)
    }

    @Test
    void "calculatePay should return correct gross pay"() {
        commissionedClassification.addSalesReceipt(salesReceipt)
        PayCheque payCheque = new PayCheque(today.minusWeeks(2), today)
        double grossPay = commissionedClassification.salary + salesReceipt.amount * commissionedClassification.commissionRate / 100

        assert commissionedClassification.calculatePay(payCheque) == grossPay
    }
}
