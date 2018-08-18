package thomashan.github.io.payroll.schedule

import java.time.LocalDate

trait PaymentSchedule {
    abstract boolean isPayDate(LocalDate payDate)
}
