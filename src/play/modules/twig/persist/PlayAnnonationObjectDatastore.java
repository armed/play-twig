package play.modules.twig.persist;

import play.modules.twig.strategy.PlayAnnotationStrategy;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.vercer.engine.persist.annotation.Index;
import com.vercer.engine.persist.standard.StrategyObjectDatastore;
import com.vercer.engine.persist.strategy.FieldStrategy;

/**
 * Default ObjectDatastore implementation, which uses Play's classloader.
 * Indexing turned off by default. You can turn it on for specific fields by
 * using {@link Index} annotation.
 * 
 * @author Artem Medeu
 * 
 */
public class PlayAnnonationObjectDatastore extends StrategyObjectDatastore {

    private static boolean indexByDefault = false;

    public PlayAnnonationObjectDatastore() {
        this(DatastoreServiceFactory.getDatastoreService());
    }

    public PlayAnnonationObjectDatastore(DatastoreService datastore) {
        this(datastore, indexByDefault);
    }

    public PlayAnnonationObjectDatastore(boolean indexed) {
        this(DatastoreServiceFactory.getDatastoreService(), indexed);
    }

    public PlayAnnonationObjectDatastore(boolean indexed, int defaultVersion) {
        this(DatastoreServiceFactory.getDatastoreService(), new PlayAnnotationStrategy(indexed, defaultVersion));
    }

    public PlayAnnonationObjectDatastore(DatastoreService datastore, boolean indexed) {
        this(datastore, new PlayAnnotationStrategy(indexed, 0));
    }

    public PlayAnnonationObjectDatastore(DatastoreService datastore, FieldStrategy fields) {
        this(datastore, new PlayAnnotationStrategy(indexByDefault, 0), fields);
    }

    protected PlayAnnonationObjectDatastore(DatastoreService datastore, PlayAnnotationStrategy strategy,
            FieldStrategy fields) {
        super(datastore, strategy, strategy, strategy, fields);
    }
}
