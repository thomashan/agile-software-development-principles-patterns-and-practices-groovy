package thomashan.github.io.payroll.affiliation

import java.time.LocalDate

class UnionAffiliation implements Affiliation {
    private Map<LocalDate, ServiceCharge> serviceCharges = [:]

    void addServiceCharge(LocalDate date, double charge) {
        serviceCharges.put(date, new ServiceCharge(date, charge))
    }

    ServiceCharge getServiceCharge(LocalDate date) {
        return serviceCharges.get(date)
    }
}
