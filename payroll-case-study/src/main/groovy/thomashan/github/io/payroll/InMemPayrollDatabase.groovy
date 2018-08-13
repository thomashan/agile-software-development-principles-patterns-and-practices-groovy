package thomashan.github.io.payroll

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

    @Override
    void deleteEmployee(int employeeId) {
        if (!employees.containsKey(employeeId)) {
            throw new RuntimeException("Employee ID doesn't exist")
        }

        employees.remove(employeeId)
    }
}
