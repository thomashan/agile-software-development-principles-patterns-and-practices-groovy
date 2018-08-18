package thomashan.github.io.payroll

trait PayrollDatabase {
    abstract void addEmployee(Employee employee)

    abstract Employee getEmployee(int employeeId)

    abstract void deleteEmployee(int employeeId)

    abstract Employee getUnionMember(int memberId)

    abstract void addUnionMember(int memberId, Employee employee)

    abstract void removeUnionMember(int memberId)
}
