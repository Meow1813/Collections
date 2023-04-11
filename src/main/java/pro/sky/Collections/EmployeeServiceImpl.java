package pro.sky.Collections;

import org.springframework.stereotype.Service;
import pro.sky.Collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.Collections.Exception.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static Map<String, Employee> employees = new HashMap<>();

    public static String getKey(Employee employee) {
        return employee.getFirstName() + " " + employee.getLastName();
    }

    @Override
    public String addEmployee(String firstName, String lastName, int department, int salary) { //добавление сотрудника
        Employee newEmployee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(getKey(newEmployee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(getKey(newEmployee), newEmployee);
        return newEmployee.toString();
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) { //удаление сотрудника
        String employeeKey = firstName + " " + lastName;
        if (employees.containsKey(employeeKey)) {
            Employee employee = employees.get(employeeKey);
            employees.remove(employeeKey);
            return employee.toString();
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        String employeeKey = firstName + " " + lastName;
        if (employees.containsKey(employeeKey)) {
            return employees.get(employeeKey).toString();
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public String printEmployeeList() {
        return employees.toString();
    }
}
