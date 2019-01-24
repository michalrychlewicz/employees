package dao;

import entities.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Michal Rychlewicz
 */
@Repository
@Service
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    private final EntityManager entityManager;

   @Autowired
   public EmployeeDAOHibernateImpl(EntityManager entityManager)
   {
       this.entityManager = entityManager;
   }

    @Override
    public Employee findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class,id);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Employee> employeeQuery = session.createQuery("FROM Employees",Employee.class);
        List<Employee> employees = employeeQuery.getResultList();
        return employees;
    }

    @Override
    public void saveEmployee(Employee e) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(e);
    }

    @Override
    public void removeEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = findById(id);
        if(employee != null)
        {
            session.remove(employee);
        }
    }
}
