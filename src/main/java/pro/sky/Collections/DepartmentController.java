package pro.sky.Collections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController (DepartmentService departmentService){
        this.departmentService = departmentService;
    }
    @GetMapping(path = "/max-salary")
    public String maxSalary(@RequestParam("departmentId") int department){
        return departmentService.maxSalary(department);
    }
    @GetMapping(path = "/min-salary")
    public String minSalary(@RequestParam("departmentId") int department){
        return departmentService.minSalary(department);
    }
    @RequestMapping(path = "/all", params = "departmentId")
    public String all(@RequestParam("departmentId") int department){
        return departmentService.allDepartment(department);
    }
    @GetMapping(path = "/all")
    public String all(){
        return departmentService.allByDepartment();
    }
}
