package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("/departments")
    public Department create(@RequestBody Department department){
        return service.create(department);
    }

    @GetMapping("/departments")
    public Iterable<Department> getAll(){
        return service.findAll();
    }

    @GetMapping("/departments/{id}")
    public Department getOne(@PathVariable Long id){
        return service.findById(id);
    }

    @PutMapping("/departments/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department department){
        // WILL NOT MODIFY THE ID BTW
        return service.update(id, department);
    }

    @DeleteMapping("/departments/{id}")
    public Boolean delete(@PathVariable Long id){
        return service.delete(id);
    }

    @RestController
    public static class EmployeeController {
        @Autowired
        private io.zipcoder.persistenceapp.services.EmployeeService service;

        public EmployeeController(io.zipcoder.persistenceapp.services.EmployeeService service) {
            this.service = service;
        }

        @PostMapping("/employees")
        public io.zipcoder.persistenceapp.models.Employee create(@RequestBody io.zipcoder.persistenceapp.models.Employee employee){
            return service.create(employee);
        }

        @GetMapping("/employees")
        public Iterable<io.zipcoder.persistenceapp.models.Employee> getAll(){
            return service.findAll();
        }

        @GetMapping("/employees/{id}")
        public io.zipcoder.persistenceapp.models.Employee getOne(@PathVariable Long id){
            return service.findById(id);
        }

        @PutMapping("/employees/{id}")
        public io.zipcoder.persistenceapp.models.Employee update(@PathVariable Long id, @RequestBody io.zipcoder.persistenceapp.models.Employee employee){
            // WILL NOT MODIFY THE ID BTW
            return service.update(id, employee);
        }

        @DeleteMapping("/employees/{id}")
        public Boolean delete(@PathVariable Long id){
            return service.delete(id);
        }
    }
}
