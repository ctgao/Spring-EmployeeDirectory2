package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.DepartmentService;
import io.zipcoder.persistenceapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ManagerController {
    @Autowired
    EmployeeService employees;
    @Autowired
    DepartmentService departments;

    public ManagerController(EmployeeService employees, DepartmentService departments) {
        this.employees = employees;
        this.departments = departments;
    }

    @PutMapping("/departments/{id}/manager")
    public Department updateDeptManager(@PathVariable Long id, @RequestBody Employee manager){
        //FIRST STEP is to check for the dept and manager
        Employee theManagerInQuestion = employees.findById(manager.getId());
        Department theDepartmentInQuestion = departments.findById(id);
        //if they don't exist return nothin'
        //if they do, then set them to be the department manager
        if(theDepartmentInQuestion != null){
            theDepartmentInQuestion.setManager(theManagerInQuestion);
            if(theManagerInQuestion != null){
                employees.setAllDepartments(theDepartmentInQuestion, theManagerInQuestion);
            }
        }
        return departments.update(id, theDepartmentInQuestion);
    }

    @PutMapping("/employees/{id}/manager")
    public Employee updateEmployeeManager(@PathVariable Long id, @RequestBody Employee manager){
        //FIRST STEP is to check for the dept and manager
        Employee theManagerInQuestion = employees.findById(manager.getId());
        Employee theEmployeeInQuestion = employees.findById(id);
        //if they don't exist return nothin'
        //if they do, then set them to be the department manager
        if(theEmployeeInQuestion != null){
            theEmployeeInQuestion.setManager(theManagerInQuestion);
        }
        return employees.update(id, theEmployeeInQuestion);
    }

    @GetMapping("/manager/{id}")
    public Iterable<Employee> findAllUnderlings(@PathVariable Long id){
        return employees.findAllByManager(id);
    }

    @GetMapping("/manager")
    public Iterable<Employee> findAllWithNoManager(){
        return employees.findAllByNoManager();
    }

    @GetMapping("/departments/{id}/employees")
    public Iterable<Employee> getAllEmployeesOfDepartment(@PathVariable Long id){
        return employees.findAllByDepartment(id);
    }
}
