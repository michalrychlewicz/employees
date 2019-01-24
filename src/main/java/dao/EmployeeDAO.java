package dao;

import entities.Employee;

import java.util.List;

/**
 * @author Michal Rychlewicz
 */
public interface EmployeeDAO {

    Employee findById(int id);
    List<Employee> findAll();
    void saveEmployee(Employee e);
    void removeEmployee(int id);
}
