package io.github.thomashan.payroll.affiliation

import io.github.thomashan.payroll.PayCheque
import org.junit.jupiter.api.Test

import java.time.LocalDate

class UnionAffiliationTests {
    @Test
    void "calculateDeductions should return correct total"() {
        UnionAffiliation unionAffiliation = new UnionAffiliation(1, 20)
        LocalDate today = LocalDate.of(2018, 8, 17)
        PayCheque payCheque = new PayCheque(today.minusWeeks(1), today)

        assert unionAffiliation.calculateDeductions(payCheque) == 20
    }
}
