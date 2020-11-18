package io.github.thomashan.payroll.ui.zk


import io.github.thomashan.payroll.command.SaleReceiptCommand
import io.github.thomashan.payroll.command.TimeCardCommand
import io.github.thomashan.payroll.command.add.AddCommissionedEmployee
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import io.github.thomashan.payroll.command.update.ChangeDirectCommand
import io.github.thomashan.payroll.command.update.ChangeMemberCommand

import java.time.LocalDate

class LoadTestEmployees {
    static List<io.github.thomashan.payroll.Employee> load() {
        LocalDate today = LocalDate.now()

        new AddHourlyEmployee(1, "Employee1", "Address1", 100).execute()
        new TimeCardCommand(1, today, 38).execute()

        new AddHourlyEmployee(2, "Employee2", "Address2", 120).execute()
        new ChangeDirectCommand(2, "Bank2", "Account2").execute()
        new ChangeMemberCommand(2, 1, 10).execute()

        new AddCommissionedEmployee(3, "Employee3", "Address3", 1200, 15).execute()
        new SaleReceiptCommand(3, today, 200).execute()
        new SaleReceiptCommand(3, today.minusDays(3), 150).execute()
        new SaleReceiptCommand(3, today.minusDays(14), 1000).execute()

        new AddSalariedEmployee(4, "Employee4", "Address4", 1500).execute()
        new ChangeMemberCommand(4, 2, 15).execute()
    }
}
