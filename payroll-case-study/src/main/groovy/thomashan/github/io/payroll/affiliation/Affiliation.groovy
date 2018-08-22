package thomashan.github.io.payroll.affiliation

import thomashan.github.io.payroll.PayCheque

trait Affiliation {
    abstract double calculateDeductions(PayCheque payCheque)
}
