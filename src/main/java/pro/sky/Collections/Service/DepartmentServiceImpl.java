package pro.sky.Collections.Service;

import org.springframework.stereotype.Service;
import pro.sky.Collections.Employee;
import pro.sky.Collections.Exception.DepartmentNotFoundException;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService  {
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public int maxSalary(int department) {
        Employee result = employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(DepartmentNotFoundException::new);
        return result.getSalary();
    }

    @Override
    public int minSalary(int department) {
        Employee result = employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(DepartmentNotFoundException::new);
        return result.getSalary();
    }

    @Override
    public int sumSalary(int department) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public String allDepartment(int department) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList()).toString();
    }

    @Override
    public String allByDepartment() {
        Map<Integer, List<Employee>> employeesByDepartment = employeeService.getEmployees().values().stream().
                collect(Collectors.groupingBy(Employee::getDepartment));


        return employeesByDepartment.toString();

    }
}
