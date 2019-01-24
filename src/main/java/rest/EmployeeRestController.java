package rest;

import dao.EmployeeDAO;
import entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Michal Rychlewicz
 */
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeRestController(EmployeeDAO employeeDAO)
    {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("/employees/employee/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId)
    {
        Employee employee = employeeDAO.findById(employeeId);
        return employee;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees()
    {
        return employeeDAO.findAll();
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        employee.setId(0);
        employeeDAO.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee)
    {
        employeeDAO.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)
    {
        Employee employee = employeeDAO.findById(employeeId);
        if(employee == null)
        {
            throw new RuntimeException("Employee with given id does not exist: "+employeeId);
        }
        employeeDAO.removeEmployee(employeeId);
        return "Employee "+employee+" was removed.";
    }


}
