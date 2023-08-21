package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Iterable<Department> findAll(){
        return repository.findAll();
    }

    public Department findById(Long id){
        if(repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    public Department create(Department department){
        return repository.save(department);
    }

    public Department update(Long id, Department department){
        Department original = findById(id);
        if(original != null) {
            // update all the fields one by one, not changing the ID at ALL
            if (department.getName() != null) original.setName(department.getName());
            if (department.getManager() != null) original.setManager(department.getManager());
            // now save that into the database
            return repository.save(original);
        }
        return null;
    }

    public boolean delete(Long id){
        Department original = findById(id);
        if(original != null) {
            repository.delete(original);
        }
        // might need to change this in the future? not sure
        return original != null;
    }
}
