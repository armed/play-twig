package controllers;

import play.modules.twig.Twig;

import java.util.ArrayList;

import com.google.appengine.api.datastore.QueryResultIterator;

import models.Department;
import models.Employee;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
    	
    	int count = Twig.find().type(Department.class).countResultsNow();
    	
    	if (count == 0) {
	    	Department dep = new Department();
	    	dep.name = "IT";
	    	
	    	dep.employees = new ArrayList<Employee>();
	    	Employee emp1 = new Employee();
	    	emp1.fullName = "John Smith";
	    	emp1.email = "jsmith@gmail.com";
	    	
	    	Employee emp2 = new Employee();
	    	emp2.fullName = "John Wayne";
	    	emp2.email = "jwayne@gmail.com";
	    	
	    	dep.employees.add(emp1);
	    	dep.employees.add(emp2);
	    	
	    	dep.store();
    	}
    	
    	QueryResultIterator<Department> depsIterator = Twig.find(Department.class);
    	
    	Department result = null;
    	
    	if (depsIterator.hasNext()) {
    		result = depsIterator.next();
    	}
    	
    	render(result);
    }
}