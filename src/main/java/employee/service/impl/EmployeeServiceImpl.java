package employee.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import employee.conf.HibernateConf;
import employee.entity.Employee;
import employee.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{
	
	private final SessionFactory sessionFactory = HibernateConf.getSessionFactory();

	public List<Employee> getAllEmployees() {
		Session session = sessionFactory.openSession();
		List<Employee> list = (List<Employee>)session.createQuery("FROM Employee").list();
		session.close();
		List<Employee> employees= list;
		return employees;
	}

	public List<Employee> findEmployeeById(Long id) {
		Session session = sessionFactory.openSession();
		List<Employee> employees = (List<Employee>)session.createQuery("FROM Employee WHERE id=:id").setParameter("id", id).list();
		session.close();
		return employees;
	}

	public List<Employee> findEmployeeByIC(String ic) {
		Session session = sessionFactory.openSession();
		List<Employee> employees = (List<Employee>)session.createQuery("FROM Employee WHERE ic=:ic").setParameter("ic", ic).list();
		session.close();
		return employees;
	}

	public void createEmployee(Employee employee) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(employee);
		tx.commit();
		session.close();
	}

	public void updateEmployee(Long id, Employee employee) {
		Session session = sessionFactory.openSession();
		List<Employee> employees = (List<Employee>)session.createQuery("FROM Employee WHERE id=:id").setParameter("id", id).list();
		Employee employeeExists = employees.get(0);
		Transaction tx = session.beginTransaction();
		employeeExists.setAddress(employee.getAddress());
		employeeExists.setFirstName(employee.getFirstName());
		employeeExists.setIc(employee.getIc());
		employeeExists.setLastName(employee.getLastName());
		employeeExists.setPhone(employee.getPhone());
		session.merge(employeeExists);
		tx.commit();
		session.close();
	}

	public void removeEmployee(Long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Employee> employees = (List<Employee>)session.createQuery("FROM Employee WHERE id=:id").setParameter("id", id).list();
		Employee employeeExists = employees.get(0);
		session.remove(employeeExists);
		tx.commit();
		session.close();
	}

}
