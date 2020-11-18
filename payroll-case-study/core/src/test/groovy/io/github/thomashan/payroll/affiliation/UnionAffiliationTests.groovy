package io.github.thomashan.payroll.affiliation


import org.junit.jupiter.api.Test

import java.time.LocalDate

class UnionAffiliationTests {
    @Test
    void "calculateDeductions should return correct total"() {
        UnionAffiliation unionAffiliation = new UnionAffiliation(1, 20)
        LocalDate today = LocalDate.of(2018, 8, 17)
        io.github.thomashan.payroll.PayCheque payCheque = new io.github.thomashan.payroll.PayCheque(today.minusWeeks(1), today)

        assert unionAffiliation.calculateDeductions(payCheque) == 20
    }
}
