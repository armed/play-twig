package models;

import com.vercer.engine.persist.annotation.Key;
import com.vercer.engine.persist.annotation.Parent;
import play.modules.twig.TwigModel;

public class Employee extends TwigModel {
		
	public String fullName;
	@Key
	public String email;
	
	@Parent
	public Department department; 
}
