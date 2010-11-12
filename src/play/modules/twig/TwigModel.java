package play.modules.twig;

import java.util.Collection;
import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.vercer.engine.persist.FindCommand.RootFindCommand;
import com.vercer.engine.persist.ObjectDatastore;
import com.vercer.engine.persist.annotation.AnnotationObjectDatastore;

public class TwigModel {
	
	public static ObjectDatastore ds() {
		return new AnnotationObjectDatastore();
	}
	
	public <T extends TwigModel> RootFindCommand<? extends TwigModel> find() {
		return ds().find().type(getClass());
	}
	
	public Key store() {
		return ds().store().instance(this).returnKeyNow();
	}
	
	public static Map<? extends TwigModel, Key> storeAll(Collection<? extends TwigModel> entities) {
		return ds().store().instances(entities).returnKeysNow();		
	}
}
