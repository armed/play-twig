package models;

import java.util.List;
import com.vercer.engine.persist.annotation.Child;
import com.vercer.engine.persist.annotation.Key;

import play.modules.twig.TwigModel;

public class Department extends TwigModel {
		
	@Key
	public String name;
	
	@Child
	public List<Employee> employees;
}
