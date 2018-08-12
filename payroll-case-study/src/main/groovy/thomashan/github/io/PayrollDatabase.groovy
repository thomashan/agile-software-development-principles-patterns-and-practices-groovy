package thomashan.github.io

trait PayrollDatabase {
    abstract void addEmployee(Employee employee)

    abstract Employee getEmployee(int employeeId)
}
