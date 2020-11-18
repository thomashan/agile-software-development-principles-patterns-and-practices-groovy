package io.github.thomashan.payroll.classification


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.time.LocalDate

class CommissionedClassificationTests {
    private LocalDate today = LocalDate.now()
    private CommissionedClassification commissionedClassification
    private io.github.thomashan.payroll.SalesReceipt salesReceipt
    private double salary = 1000
    private double commissionRate = 20

    @BeforeEach
    void setUp() {
        commissionedClassification = new CommissionedClassification(salary, commissionRate)
        salesReceipt = new io.github.thomashan.payroll.SalesReceipt(today, 30)
    }

    @Test
    void "addSalesReceipt should execute without error"() {
        commissionedClassification.addSalesReceipt(salesReceipt)
    }

    @Test
    void "calculatePay should return pro rata salary"() {
        io.github.thomashan.payroll.PayCheque payCheque = new io.github.thomashan.payroll.PayCheque(today.minusWeeks(2), today)
        double grossPay = proRataSalary(salary)

        assert commissionedClassification.calculatePay(payCheque) == grossPay
    }

    @Test
    void "calculatePay should return correct gross pay with all sales receipt within the pay cycle included"() {
        commissionedClassification.addSalesReceipt(new io.github.thomashan.payroll.SalesReceipt(today.minusWeeks(2), 70))
        commissionedClassification.addSalesReceipt(new io.github.thomashan.payroll.SalesReceipt(today.minusDays(1), 50))
        commissionedClassification.addSalesReceipt(salesReceipt)
        commissionedClassification.addSalesReceipt(new io.github.thomashan.payroll.SalesReceipt(today.plusDays(1), 100))
        io.github.thomashan.payroll.PayCheque payCheque = new io.github.thomashan.payroll.PayCheque(today.minusWeeks(2), today)
        double proRataSalary = proRataSalary(salary)
        double commissions = [50, salesReceipt.amount]*.multiply(commissionRate / 100).sum()
        double grossPay = proRataSalary + commissions

        assert commissionedClassification.calculatePay(payCheque) == grossPay
    }

    private double proRataSalary(double salary) {
        return 14 / io.github.thomashan.payroll.DateTimeUtil.daysInMonth(today) * salary
    }
}
