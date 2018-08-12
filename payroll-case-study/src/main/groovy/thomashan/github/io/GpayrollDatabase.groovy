package thomashan.github.io

class GpayrollDatabase {
    private static Map<Integer, Employee> employees = [:]

    static void addEmployee(Employee employee) {
        employees.put(employee.employeeId, employee)
    }

    static Employee getEmployee(int employeeId) {
        return employees.get(employeeId)
    }
}
