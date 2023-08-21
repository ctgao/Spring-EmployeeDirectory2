package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Iterable<Employee> findAll(){
        return repository.findAll();
    }

    public Iterable<Employee> findAllByManager(Long id){
        Iterable<Employee> allEmployees = repository.findAll();
        List<Employee> underlings = new ArrayList<>();
        for(Employee e : allEmployees){
            if(e.getManager() != null && e.getManager().getId() == id){
                underlings.add(e);
            }
        }
        return underlings;
    }

    public Iterable<Employee> findAllByNoManager(){
        Iterable<Employee> allEmployees = repository.findAll();
        List<Employee> underlings = new ArrayList<>();
        for(Employee e : allEmployees){
            if(e.getManager() == null){
                underlings.add(e);
            }
        }
        return underlings;
    }

    public Iterable<Employee> findAllByDepartment(Long dept_id){
        Iterable<Employee> allEmployees = repository.findAll();
        List<Employee> underlings = new ArrayList<>();
        for(Employee e : allEmployees){
            if(e.getDepartment() != null && e.getDepartment().getId() == dept_id){
                underlings.add(e);
            }
        }
        return underlings;
    }

    public Employee findById(Long id){
        if(repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    public Employee create(Employee employee){
        return repository.save(employee);
    }

    public Employee update(Long id, Employee employee){
        Employee original = findById(id);
        if(original != null) {
            // update all the fields one by one, not changing the ID at ALL
            if (employee.getFirstName() != null) original.setFirstName(employee.getFirstName());
            if (employee.getLastName() != null) original.setLastName(employee.getLastName());
            if (employee.getTitle() != null) original.setTitle(employee.getTitle());
            if (employee.getPhoneNumber() != null) original.setPhoneNumber(employee.getPhoneNumber());
            if (employee.getHireDate() != null) original.setHireDate(employee.getHireDate());
            if (employee.getManager() != null) original.setManager(employee.getManager());
            if (employee.getDepartment() != null) original.setDepartment(employee.getDepartment());
            // now save that into the database
            return repository.save(original);
        }
        return null;
    }

    public boolean delete(Long id){
        Employee original = findById(id);
        if(original != null) {
            repository.delete(original);
        }
        // might need to change this in the future? not sure
        return original != null;
    }

    // The two parameters will always be NOT NULL
    public void setAllDepartments(Department theDepartmentInQuestion, Employee theManagerInQuestion) {
        //first set the manager's dept
        theManagerInQuestion.setDepartment(theDepartmentInQuestion);
        //then set the underlings dept
        findAllByManager(theManagerInQuestion.getId()).forEach(e -> e.setDepartment(theDepartmentInQuestion));
    }
}
