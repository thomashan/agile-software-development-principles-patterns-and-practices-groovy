package thomashan.github.io.payroll

trait PayrollDatabase {
    abstract void addEmployee(Employee employee)

    abstract Employee getEmployee(int employeeId)

    abstract void deleteEmployee(int employeeId)
}
