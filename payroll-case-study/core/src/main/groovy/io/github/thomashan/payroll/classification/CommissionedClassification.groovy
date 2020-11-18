package io.github.thomashan.payroll.classification


import java.time.Period

import static java.time.temporal.ChronoUnit.DAYS
import static io.github.thomashan.payroll.DateTimeUtil.daysInMonth

class CommissionedClassification implements PaymentClassification {
    private final List<io.github.thomashan.payroll.SalesReceipt> salesReceipts = []
    final double salary
    final double commissionRate

    CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary
        this.commissionRate = commissionRate
    }

    void addSalesReceipt(io.github.thomashan.payroll.SalesReceipt salesReceipt) {
        salesReceipts.add(salesReceipt)
    }

    @Override
    double calculatePay(io.github.thomashan.payroll.PayCheque payCheque) {
        long daysInPayCycle = Period.between(payCheque.payPeriodStartDate, payCheque.payPeriodEndDate).get(DAYS)
        double proRataSalary = daysInPayCycle / daysInMonth(payCheque.payDate) * salary
        double commissions = salesReceipts.findAll {
            it.date > payCheque.payPeriodStartDate && it.date <= payCheque.payPeriodEndDate
        }
        .collect { it.amount * commissionRate / 100 }.sum() ?: 0

        return commissions + proRataSalary
    }
}
