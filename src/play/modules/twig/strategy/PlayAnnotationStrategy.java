package play.modules.twig.strategy;

import java.lang.reflect.Type;

import play.Play;

import com.vercer.engine.persist.annotation.AnnotationStrategy;

/**
 * Clone of basic twig-persist's {@link AnnotationStrategy} except {@link PlayAnnotationStrategy#nameToType(String name)} 
 * method, which uses Play's classloader instead of default.
 * @author Artem Medeu
 *
 */
public class PlayAnnotationStrategy extends AnnotationStrategy {

	public PlayAnnotationStrategy(boolean indexPropertiesDefault, int defaultVersion) {
		super(indexPropertiesDefault, defaultVersion);
	}

	/**
	 * Using Play's classloader
	 */
	@Override
	protected Type nameToType(String name) {
		try {
			return Play.classloader.loadClass(name);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}
}
