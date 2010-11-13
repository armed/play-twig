package play.modules.twig;

import com.google.appengine.api.datastore.Key;

/**
 * Base model class. Use it only for convenience.
 * @author Artem Medeu
 *
 */
public class TwigModel {	
		
	public Key store() {
		return Twig.store(this);
	}
	
	public Key store(Object parent) {
		return Twig.store(this, parent);
	}
	
	public Key store(String keyName) {
		return Twig.store(this, keyName);
	}
	
	public Key store(String keyName, Object parent) {
		return Twig.store(this, keyName, parent);
	}
	
	public void associate() {
		Twig.associate(this);
	}
	
	public void disassociate() {
		Twig.disassociate(this);
	}
	
	public void update() {
		Twig.update(this);
	}
	
	public void delete() {
		Twig.delete(this);
	}
}
