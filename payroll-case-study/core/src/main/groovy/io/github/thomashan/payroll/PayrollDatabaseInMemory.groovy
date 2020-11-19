package io.github.thomashan.payroll

@Singleton
class PayrollDatabaseInMemory implements PayrollDatabase {
    private Map<Integer, Employee> employees = [:]
    private Map<Integer, Employee> unionMembers = [:]

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

    @Override
    Employee getUnionMember(int memberId) {
        return unionMembers.get(memberId)
    }

    @Override
    void addUnionMember(int memberId, Employee employee) {
        unionMembers.put(memberId, employee)
    }

    @Override
    void removeUnionMember(int memberId) {
        unionMembers.remove(memberId)
    }

    @Override
    List<Integer> getAllEmployeeIds() {
        return employees.keySet().toList()
    }
}
