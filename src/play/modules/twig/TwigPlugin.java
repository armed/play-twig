package play.modules.twig;

import java.util.List;

import play.Play;
import play.PlayPlugin;
import play.modules.gae.GAEPlugin;

import com.vercer.engine.persist.ObjectDatastore;

/**
 * {@link PlayPlugin} for twig-persist - аn object datastore for Google App
 * Engine/J. Inspired by David Cheong's Objectify plugin.
 * 
 * @author Artem Medeu
 * 
 */
public class TwigPlugin extends PlayPlugin {

	protected static Boolean prod;

	/**
	 * Set up custom user datastore by getting class name from application.conf
	 */
	@SuppressWarnings("unchecked")
	private void setupDatastore() {
		String twigDatastoreName = Play.configuration.getProperty("twig.datastore");

		if (twigDatastoreName != null) {
			try {
				TwigDatastoreFactory.setCutomDatastoreType(
						(Class<? extends ObjectDatastore>) Play.classloader.loadClass(twigDatastoreName));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Unable to load datastore class: " + twigDatastoreName, e);
			}
		}
	}

	/**
	 * setup, when running on GAE environment
	 */
	@Override
	public void onApplicationStart() {
		if (isProd()) {
			setupDatastore();
		}
	}

	/**
	 * setup, when running on developer's machine
	 */
	@Override
	public void beforeInvocation() {
		if (!isProd()) {
			setupDatastore();
		}
	}

	/**
	 * checks if GAE plugin in production mode
	 */
	protected boolean isProd() {
		if (prod == null) {
			List<PlayPlugin> plugins = Play.plugins;
			for (PlayPlugin plugin : plugins) {
				if (plugin instanceof GAEPlugin) {
					prod = ((GAEPlugin) plugin).prodGAE;
					return prod;
				}
			}
			throw new IllegalStateException("Unable to determine GAE environment as GAEPlugin was not detected");
		} else {
			return prod;
		}
	}
}
