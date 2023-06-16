package pro.sky.Collections.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Collections.Employee;
import pro.sky.Collections.Exception.DepartmentNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    private DepartmentServiceImpl out;

    @BeforeEach
    public void setUp() {
        out = new DepartmentServiceImpl(employeeService);
    }

    private final Map<String, Employee> EMPLOYEES = Map.of(
            "Иван Иванов", new Employee("Иван", "Иванов", 1, 30000),
            "Андрей Иванов", new Employee("Андрей", "Иванов", 1, 40000),
            "Иван Андреев", new Employee("Иван", "Андреев", 2, 50000),
            "Дмитрий Петров", new Employee("Дмитрий", "Петров", 2, 40000),
            "Антон Иванов", new Employee("Антон", "Иванов", 2, 70000));
    private final Map<String, Employee> EMPLOYEES_EMPTY = new HashMap<>();

    @Test
    void maxSalary() {
        when(employeeService.getEmployees()).thenReturn(EMPLOYEES);
        assertEquals(70000, out.maxSalary(2));
    }

    @Test
    void maxSalaryWIthEmptyMapOrEmptyDepartment() {
        when(employeeService.getEmployees()).thenReturn(EMPLOYEES_EMPTY);
        assertThrows(DepartmentNotFoundException.class, () -> out.maxSalary(2));
    }

    @Test
    void minSalary() {
        when(employeeService.getEmployees()).thenReturn(EMPLOYEES);
        assertEquals(40000, out.minSalary(2));
    }

    @Test
    void minSalaryWIthEmptyMapOrEmptyDepartment() {
        when(employeeService.getEmployees()).thenReturn(EMPLOYEES_EMPTY);
        assertThrows(DepartmentNotFoundException.class, () -> out.minSalary(2));
    }


    @Test
    void sumSalaryWIthEmptyMapOrEmptyDepartment() {
        when(employeeService.getEmployees()).thenReturn(EMPLOYEES_EMPTY);
        assertEquals(0, out.sumSalary(2));
    }

    @Test
    void sumSalary() {
        when(employeeService.getEmployees()).thenReturn(EMPLOYEES);
        assertEquals(160000, out.sumSalary(2));
    }

    @Test
    void allDepartmentWIthEmptyMapOrEmptyDepartment() {
        when(employeeService.getEmployees()).thenReturn(EMPLOYEES_EMPTY);
        assertEquals("[]", out.allDepartment(2));
    }

    @Test
    void allByDepartmentWIthEmptyMapOrEmptyDepartment() {
        when(employeeService.getEmployees()).thenReturn(EMPLOYEES_EMPTY);
        assertEquals("{}", out.allByDepartment());
    }
}