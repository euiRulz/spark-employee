package employee.service;

import java.util.List;

import employee.entity.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees();
	public List<Employee> findEmployeeById(Long id);
	public List<Employee> findEmployeeByIC(String ic);
	public void createEmployee(Employee employee);
	public void updateEmployee(Long id,Employee employee);
	public void removeEmployee(Long id);
}
