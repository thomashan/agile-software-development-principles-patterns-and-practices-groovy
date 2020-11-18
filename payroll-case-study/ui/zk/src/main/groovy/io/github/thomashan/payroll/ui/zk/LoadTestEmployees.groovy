package io.github.thomashan.payroll.ui.zk


import io.github.thomashan.payroll.command.AddSalesReceipt
import io.github.thomashan.payroll.command.AddTimeCard
import io.github.thomashan.payroll.command.add.AddCommissionedEmployee
import io.github.thomashan.payroll.command.add.AddHourlyEmployee
import io.github.thomashan.payroll.command.add.AddSalariedEmployee
import io.github.thomashan.payroll.command.update.ChangeDirect
import io.github.thomashan.payroll.command.update.ChangeMember

import java.time.LocalDate

class LoadTestEmployees {
    static List<io.github.thomashan.payroll.Employee> load() {
        LocalDate today = LocalDate.now()

        new AddHourlyEmployee(1, "Employee1", "Address1", 100).execute()
        new AddTimeCard(1, today, 38).execute()

        new AddHourlyEmployee(2, "Employee2", "Address2", 120).execute()
        new ChangeDirect(2, "Bank2", "Account2").execute()
        new ChangeMember(2, 1, 10).execute()

        new AddCommissionedEmployee(3, "Employee3", "Address3", 1200, 15).execute()
        new AddSalesReceipt(3, today, 200).execute()
        new AddSalesReceipt(3, today.minusDays(3), 150).execute()
        new AddSalesReceipt(3, today.minusDays(14), 1000).execute()

        new AddSalariedEmployee(4, "Employee4", "Address4", 1500).execute()
        new ChangeMember(4, 2, 15).execute()
    }
}
