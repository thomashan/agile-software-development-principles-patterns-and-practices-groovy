package io.github.thomashan.payroll.classification

import io.github.thomashan.payroll.PayCheque
import io.github.thomashan.payroll.SalesReceipt

import java.time.Period

import static io.github.thomashan.payroll.DateTimeUtil.daysInMonth
import static java.time.temporal.ChronoUnit.DAYS

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
        long daysInPayCycle = Period.between(payCheque.payPeriodStartDate, payCheque.payPeriodEndDate).get(DAYS)
        double proRataSalary = daysInPayCycle / daysInMonth(payCheque.payDate) * salary
        double commissions = salesReceipts.findAll {
            it.date > payCheque.payPeriodStartDate && it.date <= payCheque.payPeriodEndDate
        }
                .collect { it.amount * commissionRate / 100 }.sum() ?: 0

        return commissions + proRataSalary
    }
}
