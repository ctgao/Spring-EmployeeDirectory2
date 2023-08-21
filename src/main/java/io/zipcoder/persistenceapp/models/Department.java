package io.zipcoder.persistenceapp.models;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dept_num")
    Long id;
    String name;
    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    Employee manager;
//    @OneToMany(mappedBy = "employee")
//    Set<Employee> dept_workers;

    public Department() {
        this(null, null);
    }

    public Department(String name, Employee manager) {
        this.name = name;
        this.manager = manager;
    }

    public Department(Long id, String name, Employee manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long department_id) {
        this.id = department_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
