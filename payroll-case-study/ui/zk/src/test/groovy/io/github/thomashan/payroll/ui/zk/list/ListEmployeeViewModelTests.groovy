package io.github.thomashan.payroll.ui.zk.list

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class ListEmployeeViewModelTests {
    private ListEmployeeViewModel listEmployeeViewModel

    @AfterEach
    void tearDown() {
        List<Integer> employeeIds = listEmployeeViewModel.payrollDatabase.allEmployeeIds
        employeeIds.each { listEmployeeViewModel.payrollDatabase.deleteEmployee(it) }
    }

    @Test
    void "load all test data"() {
        listEmployeeViewModel = new ListEmployeeViewModel()

        assert listEmployeeViewModel.employees.size() == 4
    }
}
