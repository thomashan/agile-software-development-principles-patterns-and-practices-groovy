package io.github.thomashan.payroll.command

import io.github.thomashan.command.CommandTests
import io.github.thomashan.payroll.PayCheque
import io.github.thomashan.payroll.command.add.AddCommissionedEmployee
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static io.github.thomashan.payroll.DateTimeUtil.daysInMonth

class PayTests implements CommandTests {
    @Test
    void "pay single salaried employee on the last day of the month"() {
        double monthlySalary = 1000
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", monthlySalary).execute()
        LocalDate payDate = LocalDate.of(2001, 11, 30)
        Pay paydayCommand = new Pay(payDate)
        paydayCommand.execute()

        PayCheque payCheque = paydayCommand.getPayCheque(employeeId).get()

        assert payCheque.payPeriodEndDate == payDate
        assert payCheque.grossPay == monthlySalary
        assert payCheque.deductions == 0
        assert payCheque.netPay == monthlySalary
    }

    @Test
    void "pay single salaried employee on the wrong date"() {
        double monthlySalary = 1000
        new AddSalariedEmployee(employeeId, "AnonName", "AnonAddress", monthlySalary).execute()
        LocalDate payDate = LocalDate.of(2001, 11, 29)
        Pay paydayCommand = new Pay(payDate)
        paydayCommand.execute()

        assert !paydayCommand.getPayCheque(employeeId).present
    }

    @Test
    void "pay single hourly employee one card"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 15.25).execute()
        LocalDate payDate = LocalDate.of(2001, 11, 9)
        Pay paydayCommand = new Pay(payDate)
        paydayCommand.execute()

        PayCheque payCheque = paydayCommand.getPayCheque(employeeId).get()

        assert payCheque.payDate == payDate
        assert payCheque.grossPay == 0
        assert payCheque.deductions == 0
        assert payCheque.netPay == 0
    }

    @Test
    void "pay single hourly employee on the wrong date"() {
        new AddHourlyEmployee(employeeId, "AnonName", "AnonAddress", 15.25).execute()
        LocalDate payDate = LocalDate.of(2001, 11, 8)
        Pay paydayCommand = new Pay(payDate)
        paydayCommand.execute()

        assert !paydayCommand.getPayCheque(employeeId).present
    }

    @Test
    void "pay single commissioned employee on Friday of the second week"() {
        double monthlySalary = 1000
        double commissionRate = 20
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", monthlySalary, commissionRate).execute()
        LocalDate payDate = LocalDate.of(2018, 1, 26)
        new AddSalesReceipt(employeeId, payDate.minusWeeks(2), 123).execute()
        new AddSalesReceipt(employeeId, payDate.minusDays(1), 184).execute()
        new AddSalesReceipt(employeeId, payDate, 903).execute()
        new AddSalesReceipt(employeeId, payDate.plusDays(1), 787).execute()
        double proRataSalary = 14 / daysInMonth(payDate) * monthlySalary
        double commissions = [184, 903]*.multiply(commissionRate / 100).sum()
        double grossPay = proRataSalary + commissions

        Pay paydayCommand = new Pay(payDate)
        paydayCommand.execute()

        PayCheque payCheque = paydayCommand.getPayCheque(employeeId).get()

        assert payCheque.payDate == payDate
        assert payCheque.grossPay == grossPay
        assert payCheque.deductions == 0
        assert payCheque.netPay == grossPay
    }

    @Test
    void "pay single commissioned employee on wrong date"() {
        double monthlySalary = 1000
        double commissionRate = 20
        new AddCommissionedEmployee(employeeId, "AnonName", "AnonAddress", monthlySalary, commissionRate).execute()
        LocalDate payDate = LocalDate.of(2018, 1, 25)

        Pay paydayCommand = new Pay(payDate)
        paydayCommand.execute()

        assert !paydayCommand.getPayCheque(employeeId).present
    }
}
