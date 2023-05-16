package pro.sky.Collections;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

import static pro.sky.Collections.EmployeeServiceImpl.employees;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Override
    public String maxSalary(int department) {
       return employees.values().stream()
                .filter(employee -> employee.getDepartment()==department)
                .max(Comparator.comparingInt(Employee::getSalary)).toString();
    }

    @Override
    public String minSalary(int department) {
        return employees.values().stream()
                .filter(employee -> employee.getDepartment()==department)
                .min(Comparator.comparingInt(Employee::getSalary)).toString();
    }

    @Override
    public String allDepartment(int department) {
        return employees.values().stream()
                .filter(employee -> employee.getDepartment()==department)
                .collect(Collectors.toList()).toString();
    }

    @Override
    public String allByDepartment() {

        return employees.values().stream()
                .sorted(Comparator.comparingInt(Employee ::getDepartment))
                .map(e -> e +" "+  e.getDepartment())
                .collect(Collectors.toList()).toString();

    }
}
