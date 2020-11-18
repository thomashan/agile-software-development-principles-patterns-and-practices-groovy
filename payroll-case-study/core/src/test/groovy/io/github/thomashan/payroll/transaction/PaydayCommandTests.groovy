package io.github.thomashan.payroll.transaction


import org.junit.jupiter.api.Test

import java.time.LocalDate

import static io.github.thomashan.payroll.DateTimeUtil.daysInMonth

class PaydayCommandTests implements CommandTests {
    @Test
    void "pay single salaried employee on the last day of the month"() {
        double monthlySalary = 1000
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", monthlySalary).execute()
        LocalDate payDate = LocalDate.of(2001, 11, 30)
        PaydayCommand paydayTransaction = new PaydayCommand(payDate)
        paydayTransaction.execute()

        io.github.thomashan.payroll.PayCheque payCheque = paydayTransaction.getPayCheque(employeeId).get()

        assert payCheque.payPeriodEndDate == payDate
        assert payCheque.grossPay == monthlySalary
        assert payCheque.deductions == 0
        assert payCheque.netPay == monthlySalary
    }

    @Test
    void "pay single salaried employee on the wrong date"() {
        double monthlySalary = 1000
        new io.github.thomashan.payroll.transaction.add.AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", monthlySalary).execute()
        LocalDate payDate = LocalDate.of(2001, 11, 29)
        PaydayCommand paydayTransaction = new PaydayCommand(payDate)
        paydayTransaction.execute()

        assert !paydayTransaction.getPayCheque(employeeId).present
    }

    @Test
    void "pay single hourly employee one card"() {
        new io.github.thomashan.payroll.transaction.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 15.25).execute()
        LocalDate payDate = LocalDate.of(2001, 11, 9)
        PaydayCommand paydayTransaction = new PaydayCommand(payDate)
        paydayTransaction.execute()

        io.github.thomashan.payroll.PayCheque payCheque = paydayTransaction.getPayCheque(employeeId).get()

        assert payCheque.payDate == payDate
        assert payCheque.grossPay == 0
        assert payCheque.deductions == 0
        assert payCheque.netPay == 0
    }

    @Test
    void "pay single hourly employee on the wrong date"() {
        new io.github.thomashan.payroll.transaction.add.AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 15.25).execute()
        LocalDate payDate = LocalDate.of(2001, 11, 8)
        PaydayCommand paydayTransaction = new PaydayCommand(payDate)
        paydayTransaction.execute()

        assert !paydayTransaction.getPayCheque(employeeId).present
    }

    @Test
    void "pay single commissioned employee on Friday of the second week"() {
        double monthlySalary = 1000
        double commissionRate = 20
        new io.github.thomashan.payroll.transaction.add.AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", monthlySalary, commissionRate).execute()
        LocalDate payDate = LocalDate.of(2018, 1, 26)
        new SaleReceiptCommand(employeeId, payDate.minusWeeks(2), 123).execute()
        new SaleReceiptCommand(employeeId, payDate.minusDays(1), 184).execute()
        new SaleReceiptCommand(employeeId, payDate, 903).execute()
        new SaleReceiptCommand(employeeId, payDate.plusDays(1), 787).execute()
        double proRataSalary = 14 / daysInMonth(payDate) * monthlySalary
        double commissions = [184, 903]*.multiply(commissionRate / 100).sum()
        double grossPay = proRataSalary + commissions

        PaydayCommand paydayTransaction = new PaydayCommand(payDate)
        paydayTransaction.execute()

        io.github.thomashan.payroll.PayCheque payCheque = paydayTransaction.getPayCheque(employeeId).get()

        assert payCheque.payDate == payDate
        assert payCheque.grossPay == grossPay
        assert payCheque.deductions == 0
        assert payCheque.netPay == grossPay
    }

    @Test
    void "pay single commissioned employee on wrong date"() {
        double monthlySalary = 1000
        double commissionRate = 20
        new io.github.thomashan.payroll.transaction.add.AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", monthlySalary, commissionRate).execute()
        LocalDate payDate = LocalDate.of(2018, 1, 25)

        PaydayCommand paydayTransaction = new PaydayCommand(payDate)
        paydayTransaction.execute()

        assert !paydayTransaction.getPayCheque(employeeId).present
    }
}
