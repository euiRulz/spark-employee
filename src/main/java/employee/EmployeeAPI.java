package employee;

import static spark.Spark.*;

import java.util.List;

import com.google.gson.Gson;

import employee.entity.Employee;
import employee.service.EmployeeService;
import employee.service.impl.EmployeeServiceImpl;
import employee.util.StandardResponse;
import employee.util.StatusResponse;

public class EmployeeAPI {
	public static void main(String args[]){
		port(8780);
		int p = 10;
		int q = 10;
		int z = p + q;
		int x = 10;
		int y = 10;
		int z = x + y;
		EmployeeService empService = new EmployeeServiceImpl();
		
		get("/employees", (request,response)->{
			List<Employee> employees = empService.getAllEmployees();
			StandardResponse sr = new StandardResponse(StatusResponse.SUCCESS,"list of employees", new Gson().toJsonTree(employees));
			response.status(200);
			response.type("application/json");
			return new Gson().toJson(sr);
		});
		
		post("/employee",(request,response)->{
			Employee employee = new Gson().fromJson(request.body(), Employee.class);
			empService.createEmployee(employee);
			response.status(201);
			response.type("application/json");
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "employee created"));
		});
		
		put("/employee/:id",(request,response)->{
			Employee employee = new Gson().fromJson(request.body(), Employee.class);
			Long id = Long.parseLong(request.params("id"));
			empService.updateEmployee(id, employee);
			response.status(200);
			response.type("application/json");
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "employee created"));
		});
		
		get("/employee/:id",(request,response)->{
			Long id = Long.parseLong(request.params("id"));
			StandardResponse sr = new StandardResponse(StatusResponse.SUCCESS,"employee details of id="+id, new Gson().toJsonTree(
					empService.findEmployeeById(id)));
			response.status(200);
			response.type("application/json");
			return new Gson().toJson(sr);
		});
		
		get("/employee/:ic",(request,response)->{
			String ic = request.params("ic");
			StandardResponse sr = new StandardResponse(StatusResponse.SUCCESS,"employee details of id="+ic, new Gson().toJsonTree(
					empService.findEmployeeByIC(ic)));
			response.status(200);
			response.type("application/json");
			return new Gson().toJson(sr);
		});
		
	}
}

