package pro.sky.Collections;

import org.springframework.stereotype.Service;
import pro.sky.Collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.Collections.Exception.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static Map<String, Employee> employees = new HashMap<>();
    public static String getKey(Employee employee){
        return employee.getFirstName() + " " + employee.getLastName();
    }

    @Override
    public String addEmployee(String firstName, String lastName) { //добавление сотрудника
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(newEmployee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(getKey(newEmployee),newEmployee);
        return newEmployee.toString();
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) { //удаление сотрудника
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(employee))) {
            employees.remove(getKey(employee));
            return employee.toString();
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getKey(employee))) {
            return employee.toString();
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public String printEmployeeList() {
        return employees.toString();
    }
}
