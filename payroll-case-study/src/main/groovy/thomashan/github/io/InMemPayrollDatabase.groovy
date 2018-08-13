package thomashan.github.io

@Singleton
class InMemPayrollDatabase implements PayrollDatabase {
    private Map<Integer, Employee> employees = [:]

    @Override
    void addEmployee(Employee employee) {
        employees.put(employee.employeeId, employee)
    }

    @Override
    Employee getEmployee(int employeeId) {
        return employees.get(employeeId)
    }
}
