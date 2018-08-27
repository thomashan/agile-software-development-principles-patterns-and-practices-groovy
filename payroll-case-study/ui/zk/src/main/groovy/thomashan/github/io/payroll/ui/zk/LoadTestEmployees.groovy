package thomashan.github.io.payroll.ui.zk

import thomashan.github.io.payroll.Employee
import thomashan.github.io.payroll.transaction.SaleReceiptTransaction
import thomashan.github.io.payroll.transaction.TimeCardTransaction
import thomashan.github.io.payroll.transaction.add.AddCommissionedEmployee
import thomashan.github.io.payroll.transaction.add.AddHourlyEmployee
import thomashan.github.io.payroll.transaction.add.AddSalariedEmployee
import thomashan.github.io.payroll.transaction.update.ChangeDirectTransaction
import thomashan.github.io.payroll.transaction.update.ChangeMemberTransaction

import java.time.LocalDate

class LoadTestEmployees {
    static List<Employee> load() {
        LocalDate today = LocalDate.now()

        new AddHourlyEmployee(1, "Employee1", "Address1", 100).execute()
        new TimeCardTransaction(1, today, 38).execute()

        new AddHourlyEmployee(2, "Employee2", "Address2", 120).execute()
        new ChangeDirectTransaction(2, "Bank2", "Account2").execute()
        new ChangeMemberTransaction(2, 1, 10).execute()

        new AddCommissionedEmployee(3, "Employee3", "Address2", 1200, 15).execute()
        new SaleReceiptTransaction(3, today, 200).execute()
        new SaleReceiptTransaction(3, today.minusDays(3), 150).execute()
        new SaleReceiptTransaction(3, today.minusDays(14), 1000).execute()

        new AddSalariedEmployee(4, "Employee4", "Address4", 1500).execute()
        new ChangeMemberTransaction(4, 2, 15).execute()
    }
}
