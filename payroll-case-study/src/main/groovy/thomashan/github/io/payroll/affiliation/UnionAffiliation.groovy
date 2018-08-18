package thomashan.github.io.payroll.affiliation

import java.time.LocalDate

class UnionAffiliation implements Affiliation {
    private Map<LocalDate, ServiceCharge> serviceCharges = [:]
    final double dues
    final int memberId

    UnionAffiliation(int memberId, double dues) {
        this.memberId = memberId
        this.dues = dues
    }

    void addServiceCharge(LocalDate date, double charge) {
        serviceCharges.put(date, new ServiceCharge(date, charge))
    }

    ServiceCharge getServiceCharge(LocalDate date) {
        return serviceCharges.get(date)
    }
}
