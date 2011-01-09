package play.modules.twig;

import play.modules.twig.persist.PlayAnnonationObjectDatastore;

import com.google.common.base.Preconditions;
import com.vercer.engine.persist.ObjectDatastore;

/**
 * Factory class for thread local implementation of {@link ObjectDatastore}
 * 
 * @author Artem Medeu
 * 
 */
public class TwigDatastoreFactory {
    private static ThreadLocal<ObjectDatastore> datastore = new ThreadLocal<ObjectDatastore>();

    private static Class<? extends ObjectDatastore> dsType = PlayAnnonationObjectDatastore.class;

    /**
     * This method is used by {@link TwigPlugin} to setup custom datastore from
     * application.conf if any. Otherwise {@link PlayAnnonationObjectDatastore}
     * is used as default implementation.
     */
    public static void setCutomDatastoreType(Class<? extends ObjectDatastore> dt) {
        Preconditions.checkNotNull(dt, "Custom datasource cannot be null");
        dsType = dt;
    }

    /**
     * Get initialized datastore from thread local variable or create new
     * 
     * @return thread local {@link ObjectDatastore} implementation
     */
    public static ObjectDatastore getOrInstantiateDatastore() {
        ObjectDatastore ds = datastore.get();
        if (ds == null) {
            ds = instantiateDatastore();
            datastore.set(ds);
        }
        return ds;
    }

    /**
     * Get implementation of {@link ObjectDatastore}, otherwise use
     * {@link PlayAnnonationObjectDatastore} without indexing as default
     * 
     * @return an implementation of {@link ObjectDatastore}
     */
    private static ObjectDatastore instantiateDatastore() {
        try {
            return dsType.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Cannot instantiate datastore: " + dsType.getName(), e);
        }
    }
}
