package thomashan.github.io.payroll.classification

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import thomashan.github.io.payroll.DateTimeUtil
import thomashan.github.io.payroll.PayCheque
import thomashan.github.io.payroll.SalesReceipt

import java.time.LocalDate

class CommissionedClassificationTests {
    private LocalDate today = LocalDate.now()
    private CommissionedClassification commissionedClassification
    private SalesReceipt salesReceipt
    private double salary = 1000
    private double commissionRate = 20

    @BeforeEach
    void setUp() {
        commissionedClassification = new CommissionedClassification(salary, commissionRate)
        salesReceipt = new SalesReceipt(today, 30)
    }

    @Test
    void "addSalesReceipt should execute without error"() {
        commissionedClassification.addSalesReceipt(salesReceipt)
    }

    @Test
    void "calculatePay should return pro rata salary"() {
        PayCheque payCheque = new PayCheque(today.minusWeeks(2), today)
        double grossPay = proRataSalary(salary)

        assert commissionedClassification.calculatePay(payCheque) == grossPay
    }

    @Test
    void "calculatePay should return correct gross pay with all sales receipt within the pay cycle included"() {
        commissionedClassification.addSalesReceipt(new SalesReceipt(today.minusWeeks(2), 70))
        commissionedClassification.addSalesReceipt(new SalesReceipt(today.minusDays(1), 50))
        commissionedClassification.addSalesReceipt(salesReceipt)
        commissionedClassification.addSalesReceipt(new SalesReceipt(today.plusDays(1), 100))
        PayCheque payCheque = new PayCheque(today.minusWeeks(2), today)
        double proRataSalary = proRataSalary(salary)
        double commissions = [50, salesReceipt.amount]*.multiply(commissionRate / 100).sum()
        double grossPay = proRataSalary + commissions

        assert commissionedClassification.calculatePay(payCheque) == grossPay
    }

    private double proRataSalary(double salary) {
        return 14 / DateTimeUtil.daysInMonth(today) * salary
    }
}
