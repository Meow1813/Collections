package pro.sky.Collections.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Collections.Service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController (DepartmentService departmentService){
        this.departmentService = departmentService;
    }
    @GetMapping(path = "/{id}/salary/max")
    public int maxSalary(@PathVariable("id") int id){
        return departmentService.maxSalary(id);
    }
    @GetMapping(path = "/{id}/salary/min")
    public int minSalary(@PathVariable("id") int id){
        return departmentService.minSalary(id);
    }
    @GetMapping (path = "/{id}/salary/sum")
    public int sumSalary(@PathVariable("id") int id){
        return departmentService.sumSalary(id);
    }
    @GetMapping(path = "/{id}/employees")
    public String all(@PathVariable("id") int department){
        return departmentService.allDepartment(department);
    }
    @GetMapping(path = "/employees")
    public String all(){
        return departmentService.allByDepartment();
    }
}
