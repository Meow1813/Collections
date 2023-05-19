package pro.sky.Collections.Service;

public interface DepartmentService {
    int maxSalary(int department);
    int minSalary(int department);
    int sumSalary(int department);
    String allDepartment(int department);
    String allByDepartment();
}
