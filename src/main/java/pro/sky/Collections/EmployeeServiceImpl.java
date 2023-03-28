package pro.sky.Collections;

import org.springframework.stereotype.Service;
import pro.sky.Collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.Collections.Exception.EmployeeNotFoundException;
import pro.sky.Collections.Exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static List<Employee> employees = new ArrayList<>();

    @Override
    public String addEmployee(String firstName, String lastName) { //добавление сотрудника
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(newEmployee);
        return newEmployee.toString();
    }

    @Override
    public String deleteEmployee(String firstName, String lastName) { //удаление сотрудника
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(employee)) {
                employees.remove(i);
                return employee.toString();
            }
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

            if (employees.contains(employee)) {
                return employee.toString();
            }

        throw new EmployeeNotFoundException();
    }
    @Override
    public String printEmployeeList(){
        return employees.toString();
    }
}
